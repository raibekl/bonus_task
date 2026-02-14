package com.carrental.service;

import com.carrental.dto.RentRequest;
import com.carrental.dto.RentResponse;
import com.carrental.entity.Car;
import com.carrental.entity.Customer;
import com.carrental.entity.Rental;
import com.carrental.exception.BadRequestException;
import com.carrental.repo.CarRepository;
import com.carrental.repo.CustomerRepository;
import com.carrental.repo.RentalRepository;
import com.carrental.util.PlateNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RentalService {
  private final CarRepository carRepo;
  private final CustomerRepository customerRepo;
  private final RentalRepository rentalRepo;

  public RentalService(CarRepository carRepo, CustomerRepository customerRepo, RentalRepository rentalRepo) {
    this.carRepo = carRepo;
    this.customerRepo = customerRepo;
    this.rentalRepo = rentalRepo;
  }

  @Transactional
  public RentResponse rentAvailableCarOrCreate(RentRequest req) {
    if (req.endDate.isBefore(req.startDate)) {
      throw new BadRequestException("endDate must be >= startDate");
    }

    // Create NEW customer every rent
    Customer customer = new Customer();
    customer.setName(req.customerName);
    customer.setEmail(req.email);
    customer.setPhone(req.phone);
    customer = customerRepo.save(customer);

    // Find an available car
    Car car = carRepo.findFirstByIsActiveTrueOrderByIdAsc().orElse(null);

    // If none available -> create a new car with auto plate
    if (car == null) {
      car = new Car();
      car.setName(req.newCarName);
      car.setDailyRate(req.newCarDailyRate);
      car.setActive(true);

      String plate;
      do {
        plate = PlateNumberGenerator.generate();
      } while (carRepo.existsByPlateNumber(plate));
      car.setPlateNumber(plate);

      car = carRepo.save(car);
    } else {
      // If overlap, create a new car and rent it
      if (rentalRepo.hasOverlap(car.getId(), req.startDate, req.endDate)) {
        Car created = new Car();
        created.setName(req.newCarName);
        created.setDailyRate(req.newCarDailyRate);
        created.setActive(true);

        String plate;
        do {
          plate = PlateNumberGenerator.generate();
        } while (carRepo.existsByPlateNumber(plate));
        created.setPlateNumber(plate);

        car = carRepo.save(created);
      }
    }

    // Create rental row
    Rental rental = new Rental();
    rental.setCar(car);
    rental.setCustomer(customer);
    rental.setName(car.getName());
    rental.setStartDate(req.startDate);
    rental.setEndDate(req.endDate);
    rental.setStatus("ACTIVE");
    rental = rentalRepo.save(rental);

    // Mark car not available
    car.setActive(false);
    carRepo.save(car);

    return new RentResponse(rental.getId(), car.getId(), customer.getId(), car.getPlateNumber(), rental.getStatus());
  }
}

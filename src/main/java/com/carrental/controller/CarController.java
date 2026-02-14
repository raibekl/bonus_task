package com.carrental.controller;

import com.carrental.dto.CreateCarRequest;
import com.carrental.entity.Car;
import com.carrental.repo.CarRepository;
import com.carrental.util.PlateNumberGenerator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
  private final CarRepository carRepo;

  public CarController(CarRepository carRepo) {
    this.carRepo = carRepo;
  }

  @GetMapping
  public List<Car> all() {
    return carRepo.findAll();
  }

  @PostMapping
  public Car create(@Valid @RequestBody CreateCarRequest req) {
    Car car = new Car();
    car.setName(req.name);
    car.setDailyRate(req.dailyRate);
    car.setActive(true);

    String plate;
    do {
      plate = PlateNumberGenerator.generate();
    } while (carRepo.existsByPlateNumber(plate));
    car.setPlateNumber(plate);

    return carRepo.save(car);
  }
}

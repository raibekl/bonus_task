package com.carrental.service;

import com.carrental.cache.SimpleCache;
import com.carrental.entity.Car;
import com.carrental.repo.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private static final String CARS_ALL_KEY = "cars:all";

    private final CarRepository carRepository;
    private final SimpleCache cache = SimpleCache.getInstance();

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @SuppressWarnings("unchecked")
    public List<Car> getAllCars() {
        return cache.get(CARS_ALL_KEY, List.class)
                .map(list -> (List<Car>) list)
                .orElseGet(() -> {
                    List<Car> cars = carRepository.findAll();
                    cache.put(CARS_ALL_KEY, cars);
                    return cars;
                });
    }

    public Car createCar(Car car) {
        Car saved = carRepository.save(car);
        cache.evict(CARS_ALL_KEY);
        return saved;
    }

    public Car updateCar(int id, Car car) {
        car.setId(id);
        Car updated = carRepository.save(car);
        cache.evict(CARS_ALL_KEY);
        return updated;
    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
        cache.evict(CARS_ALL_KEY);
    }
}

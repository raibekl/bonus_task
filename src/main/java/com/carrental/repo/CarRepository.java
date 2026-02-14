package com.carrental.repo;

import com.carrental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
  Optional<Car> findFirstByIsActiveTrueOrderByIdAsc();
  boolean existsByPlateNumber(String plateNumber);
}

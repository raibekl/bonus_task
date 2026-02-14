package com.carrental.repo;

import com.carrental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

  // true if the given car has an ACTIVE/RENTED rental that overlaps the requested dates
  @Query("select (count(r) > 0) from Rental r " +
         "where r.car.id = :carId " +
         "and r.status in ('ACTIVE','RENTED') " +
         "and not (:endDate < r.startDate or :startDate > r.endDate)")
  boolean hasOverlap(Integer carId, LocalDate startDate, LocalDate endDate);
}

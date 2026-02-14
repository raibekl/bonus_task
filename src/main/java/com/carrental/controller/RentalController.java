package com.carrental.controller;

import com.carrental.dto.RentRequest;
import com.carrental.dto.RentResponse;
import com.carrental.entity.Rental;
import com.carrental.repo.RentalRepository;
import com.carrental.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  private final RentalService rentalService;
  private final RentalRepository rentalRepo;

  public RentalController(RentalService rentalService, RentalRepository rentalRepo) {
    this.rentalService = rentalService;
    this.rentalRepo = rentalRepo;
  }

  @PostMapping("/rent")
  public RentResponse rent(@Valid @RequestBody RentRequest req) {
    return rentalService.rentAvailableCarOrCreate(req);
  }

  @GetMapping
  public List<Rental> all() {
    return rentalRepo.findAll();
  }
}

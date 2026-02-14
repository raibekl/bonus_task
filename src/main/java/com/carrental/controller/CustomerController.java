package com.carrental.controller;

import com.carrental.dto.CreateCustomerRequest;
import com.carrental.entity.Customer;
import com.carrental.repo.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerRepository repo;

  public CustomerController(CustomerRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Customer> all() {
    return repo.findAll();
  }

  @PostMapping
  public Customer create(@Valid @RequestBody CreateCustomerRequest req) {
    Customer c = new Customer();
    c.setName(req.name);
    c.setEmail(req.email);
    c.setPhone(req.phone);
    return repo.save(c);
  }
}

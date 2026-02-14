package com.carrental.dto;

import jakarta.validation.constraints.*;

public class CreateCustomerRequest {
  @NotBlank
  public String name;

  @Email
  @NotBlank
  public String email;

  @NotBlank
  public String phone;
}

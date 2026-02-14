package com.carrental.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RentRequest {
  @NotBlank
  public String customerName;

  @Email
  @NotBlank
  public String email;

  @NotBlank
  public String phone;

  @NotNull
  public LocalDate startDate;

  @NotNull
  public LocalDate endDate;

  @NotBlank
  public String newCarName;

  @NotNull
  @DecimalMin(value = "0.0", inclusive = false)
  public BigDecimal newCarDailyRate;
}

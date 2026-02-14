package com.carrental.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateCarRequest {
  @NotBlank
  public String name;

  @NotNull
  @DecimalMin(value = "0.0", inclusive = false)
  public BigDecimal dailyRate;
}

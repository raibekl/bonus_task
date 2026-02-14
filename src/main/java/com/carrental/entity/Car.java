package com.carrental.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 120)
  private String name;

  @Column(name = "plate_number", nullable = false, unique = true, length = 20)
  private String plateNumber;

  @Column(name = "daily_rate", nullable = false, precision = 10, scale = 2)
  private BigDecimal dailyRate;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getPlateNumber() { return plateNumber; }
  public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

  public BigDecimal getDailyRate() { return dailyRate; }
  public void setDailyRate(BigDecimal dailyRate) { this.dailyRate = dailyRate; }

  public boolean isActive() { return isActive; }
  public void setActive(boolean active) { isActive = active; }
}

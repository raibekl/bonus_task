package com.carrental.dto;

public class RentResponse {
  public Integer rentalId;
  public Integer carId;
  public Integer customerId;
  public String plateNumber;
  public String status;

  public RentResponse(Integer rentalId, Integer carId, Integer customerId, String plateNumber, String status) {
    this.rentalId = rentalId;
    this.carId = carId;
    this.customerId = customerId;
    this.plateNumber = plateNumber;
    this.status = status;
  }
}

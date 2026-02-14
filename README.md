# Car Rental API (Spring Boot + Postman)

## DB password
Set in `src/main/resources/application.properties`:
- dar200700

## Run
1) Create PostgreSQL database: `car_rental`
2) Start:
- mvn -q -DskipTests spring-boot:run
3) Open:
- http://localhost:8080/api/cars

## Postman
### Rent (checks availability; if none available -> creates a new car with auto plate)
POST http://localhost:8080/api/rentals/rent
Body JSON:
{
  "customerName": "Ali",
  "email": "ali@example.com",
  "phone": "777111222",
  "startDate": "2026-02-10",
  "endDate": "2026-02-14",
  "newCarName": "Toyota Corolla",
  "newCarDailyRate": 12000.00
}

### Create car (auto plate)
POST http://localhost:8080/api/cars
Body:
{
  "name": "Hyundai Elantra",
  "dailyRate": 14000.00
}

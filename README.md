# Parking Lot Service

This is a simple parking lot service implemented in Java. It allows you to manage a parking lot with different types of parking spots and vehicles.

## Getting Started

To run the parking lot service locally, follow these steps:

1. Clone the repository.
2. Compile the Java code.
3. Run the main application.

## API Documentations

http://localhost:8080/swagger-ui/index.html

## Postman Collection

https://elements.getpostman.com/redirect?entityId=31808609-a5557b12-f07f-4955-b67b-7760a0c2ba7b&entityType=collection

## API Endpoints

The parking lot service provides the following API endpoints:

- `PUT /parking/vehicle-leaving` 
- `POST /parking/{type}/{count}` 
- `POST /parking/vehicle` 
- `GET /parking/remaining` 
- `GET /parking/all-taken/{vehicleType}`

## Examples

```bash
# Create COMPACT Spots
curl --location --request POST 'http://localhost:8080/parking/COMPACT/5'

# Create Regular Spots
curl --location --request POST 'http://localhost:8080/parking/MOTORCYCLE/5'

# Create Motorcycle Spots
curl --location --request POST 'http://localhost:8080/parking/MOTORCYCLE/5'

# Park a CAR
curl --location 'http://localhost:8080/parking/vehicle' \
--header 'Content-Type: application/json' \
--data '{"licensePlate": "abc123", "vehicleType": "CAR"}'

# Park a MotorCycle
curl --location 'http://localhost:8080/parking/vehicle' \
--header 'Content-Type: application/json' \
--data '{"licensePlate": "xyz345", "vehicleType": "MOTORCYCLE"}'

# Park a Van
curl --location 'http://localhost:8080/parking/vehicle' \
--header 'Content-Type: application/json' \
--data '{"licensePlate": "van999", "vehicleType": "VAN"}'

# Vehicle leaving - Van case
curl --location --request PUT 'http://localhost:8080/parking/vehicle-leaving' \
--header 'Content-Type: application/json' \
--data '{"licensePlate": "van999", "vehicleType": "VAN"}'

# Get Remaining Spots
curl --location 'http://localhost:8080/parking/remaining'

# Check if all spots has been taken by VehicleType: CAR, MOTORCYCLE, VAN
curl --location 'http://localhost:8080/parking/all-taken/CAR'
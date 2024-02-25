# Parking Lot Service

This is a simple parking lot service implemented in Java. It allows you to manage a parking lot with different types of parking spots and vehicles.

## Getting Started

To run the parking lot service locally, follow these steps:

1. Clone the repository.
2. Compile the Java code.
3. Run the main application.

## API Endpoints

The parking lot service provides the following API endpoints:

- `/park`: Parks a vehicle in the parking lot.
- `/leave`: Removes a vehicle from the parking lot.
- `/spots/remaining`: Retrieves the number of remaining parking spots for each type.
- `/spots/taken/:vehicleType`: Checks if all parking spots are taken for a given vehicle type.

## Examples

```bash
# Park Vehicle
curl -X POST -H "Content-Type: application/json" -d '{"vehicleType": "CAR"}' http://localhost:8080/park

# Vehicle Leaves Parking Lot
curl -X POST -H "Content-Type: application/json" -d '{"vehicleType": "CAR"}' http://localhost:8080/leave

# Find Remaining Spots
curl http://localhost:8080/spots/remaining

# Check If All Parking Spots Are Taken
curl http://localhost:8080/spots/taken/CAR

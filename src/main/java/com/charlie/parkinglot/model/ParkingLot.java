package com.charlie.parkinglot.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingSpot> parkingSpots;

    public ParkingLot(int totalSpots) {
        this.parkingSpots = new ArrayList<>(totalSpots);
        initializeParkingSpots(totalSpots);
    }

    private void initializeParkingSpots(int totalSpots) {
        // Initialize the parking spots in the parking lot
        for (int i = 0; i < totalSpots; i++) {
            // Determine the type of parking spot based on its index
            ParkingSpot spot;
            if (i < totalSpots / 3) {
                spot = new MotorCycleSpot();
            } else if (i < totalSpots * 2 / 3) {
                spot = new CompactCarSpot();
            } else {
                spot = new RegularSpot();
            }
            parkingSpots.add(spot);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        // Find an available spot for the vehicle and park it
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false; // No available spot found
    }

    public boolean vehicleLeavesParkingLot(Vehicle vehicle) {
        // Find the spot occupied by the vehicle and remove it
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getOccupiedVehicle() == vehicle) {
                spot.removeVehicle();
                return true;
            }
        }
        return false; // Vehicle not found in the parking lot
    }

    public int findRemainingSpots() {
        // Count the number of remaining available spots
        int remainingSpots = 0;
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable()) {
                remainingSpots++;
            }
        }
        return remainingSpots;
    }

    public boolean checkIfAllSpotsTaken(VehicleType vehicleType) {
        ParkingSpotType spotType = ParkingSpotType.fromVehicleType(vehicleType);
        if (spotType == null) {
            return true; // Invalid vehicle type
        }

        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotType() == spotType && spot.isAvailable()) {
                return false; // At least one spot of the given type is available
            }
        }
        return true; // All spots of the given type are taken
    }

}

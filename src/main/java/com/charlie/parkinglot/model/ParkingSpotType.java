package com.charlie.parkinglot.model;

public enum ParkingSpotType {
    MOTORCYCLE_SPOT,
    COMPACT_CAR_SPOT,
    REGULAR_SPOT;

    public static ParkingSpotType fromVehicleType(VehicleType vehicleType) {
        switch (vehicleType) {
            case MOTORCYCLE:
                return MOTORCYCLE_SPOT;
            case CAR:
                return COMPACT_CAR_SPOT;
            case VAN:
                return REGULAR_SPOT;
            default:
                return null; // Invalid vehicle type
        }
    }
}
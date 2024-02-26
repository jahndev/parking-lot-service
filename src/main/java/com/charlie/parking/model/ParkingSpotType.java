package com.charlie.parking.model;

import java.util.*;

public enum ParkingSpotType {
    MOTORCYCLE,
    COMPACT,
    REGULAR;

    public static Set<ParkingSpotType> getByVehicleType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> Set.of(COMPACT, REGULAR);
            case MOTORCYCLE -> Set.of(MOTORCYCLE);
            case VAN -> Set.of(REGULAR);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        };
    }
}

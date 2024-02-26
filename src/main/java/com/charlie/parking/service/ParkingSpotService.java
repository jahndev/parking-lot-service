package com.charlie.parking.service;

import com.charlie.parking.model.*;

import java.util.*;

public interface ParkingSpotService {
    public List<ParkingSpot> createParkingSpotsByType(ParkingSpotType parkingSpotType, Integer count);
    List<ParkingSpot> parkVehicle(Vehicle vehicle);
    List<ParkingSpot> vehicleLeaves(Vehicle vehicle);
    Map<ParkingSpotType,Long> getRemainingSpots();
    boolean allSpotsTakenForType(VehicleType vehicleType);
}

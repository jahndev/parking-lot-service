package com.charlie.parking.service;

import com.charlie.parking.domain.*;

import java.util.*;

public interface ParkingSpotService {
    public List<ParkingSpot> createParkingSpotsByType(ParkingSpotType parkingSpotType, Integer count);
    List<ParkingSpot> parkVehicle(Vehicle vehicle);
    void vehicleLeaves(Vehicle vehicle);
    Map<ParkingSpotType,Integer> getRemainingSpots();
    boolean allSpotsTakenForType(VehicleType vehicleType);
}

package com.charlie.parking.service;

import com.charlie.parking.domain.*;

public interface VehicleService {
    void parkVehicle(VehicleSize vehicleSize, String licensePlate);
    void unparkVehicle(String licensePlate);
    int getRemainingSpots();
    boolean areAllSpotsTaken(VehicleSize vehicleSize);
}

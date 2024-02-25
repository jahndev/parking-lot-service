package com.charlie.parkinglot.model;

abstract class ParkingSpot {
    private Vehicle parkedVehicle;
    private ParkingSpotType spotType;
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
    public void removeVehicle() {
        parkedVehicle = null;
    }
    // Getter and setter for parkedVehicle
    public ParkingSpotType getSpotType() {
        return spotType;
    }
    public Vehicle getOccupiedVehicle() {
        return parkedVehicle;
    }
    public boolean canFitVehicle(VehicleType vehicleType) {
        ParkingSpotType requiredSpotType = ParkingSpotType.fromVehicleType(vehicleType);
        return requiredSpotType == spotType;
    }
    public boolean isAvailable() {
        return parkedVehicle == null;
    }
}
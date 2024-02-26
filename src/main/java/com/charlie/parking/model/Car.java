package com.charlie.parking.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
@JsonTypeName("CAR")
public class Car extends Vehicle {

    public Car() {}

    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }

    @Override
    public int getRequiredSlots() {
        return 1;
    }

    @Override
    public boolean canFitInSlot(ParkingSpot slot) {
        return slot.getParkingSpotType() == ParkingSpotType.COMPACT || slot.getParkingSpotType() == ParkingSpotType.REGULAR;
    }
}
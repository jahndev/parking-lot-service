package com.charlie.parking.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

    public Car() {
    }

    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }

    @Override
    public int getRequiredSlots() {
        return 1;
    }

    @Override
    public boolean canFitInSlot(ParkingSpot slot) {
        return slot.getType() == ParkingSpotType.COMPACT || slot.getType() == ParkingSpotType.REGULAR;
    }
}
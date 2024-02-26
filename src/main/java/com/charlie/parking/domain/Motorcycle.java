package com.charlie.parking.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle {

    public Motorcycle() {
    }

    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleSize.MOTORCYCLE);
    }

    @Override
    public int getRequiredSlots() {
        return 1;
    }

    @Override
    public boolean canFitInSlot(ParkingSlot slot) {
        return true;
    }
}


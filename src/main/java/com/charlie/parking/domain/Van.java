package com.charlie.parking.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VAN")
public class Van extends Vehicle {

    public Van() {
    }

    public Van(String licensePlate) {
        super(licensePlate, VehicleSize.REGULAR);
    }

    @Override
    public int getRequiredSlots() {
        return 3;
    }

    @Override
    public boolean canFitInSlot(ParkingSlot slot) {
        return slot.getSize() == VehicleSize.REGULAR;
    }
}
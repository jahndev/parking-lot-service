package com.charlie.parking.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MOTORCYCLE")
@JsonTypeName("MOTORCYCLE")
public class Motorcycle extends Vehicle {

    public Motorcycle() {
    }

    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }

    @Override
    public int getRequiredSlots() {
        return 1;
    }

    @Override
    public boolean canFitInSlot(ParkingSpot slot) {
        return slot.getParkingSpotType() == ParkingSpotType.MOTORCYCLE;
    }
}


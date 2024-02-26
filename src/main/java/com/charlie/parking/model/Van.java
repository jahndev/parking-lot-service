package com.charlie.parking.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VAN")
@JsonTypeName("VAN")
public class Van extends Vehicle {

    public Van() {
    }

    public Van(String licensePlate) {
        super(licensePlate, VehicleType.VAN);
    }

    @Override
    public int getRequiredSlots() {
        return 3;
    }

    @Override
    public boolean canFitInSlot(ParkingSpot slot) {
        return slot.getParkingSpotType() == ParkingSpotType.REGULAR;
    }
}
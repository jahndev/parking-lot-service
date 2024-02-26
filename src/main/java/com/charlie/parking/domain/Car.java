package com.charlie.parking.domain;

import com.charlie.parking.domain.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

    public Car() {
    }

    public Car(String licensePlate) {
        super(licensePlate, VehicleSize.COMPACT);
    }

    @Override
    public int getRequiredSlots() {
        return 1;
    }

    @Override
    public boolean canFitInSlot(ParkingSlot slot) {
        return slot.getSize() == VehicleSize.COMPACT || slot.getSize() == VehicleSize.REGULAR;
    }
}
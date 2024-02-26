package com.charlie.parking.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "vehicleType",
        defaultImpl = VehicleType.class,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "CAR"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "MOTORCYCLE"),
        @JsonSubTypes.Type(value = Van.class, name = "VAN")
})

public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public Vehicle(String licensePlate, String vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = VehicleType.valueOf(vehicleType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public abstract int getRequiredSlots();

    public abstract boolean canFitInSlot(ParkingSpot slot);
}
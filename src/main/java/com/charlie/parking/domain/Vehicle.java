package com.charlie.parking.domain;

import jakarta.persistence.*;

import java.util.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle")
    @JoinTable(name = "vehicle_parking_slot",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_slot_id"))
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
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

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public List<ParkingSpot> getParkingSlots() {
        return parkingSpots;
    }

    public void setParkingSlots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void addParkingSlot(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }

    public void removeParkingSlot(ParkingSpot parkingSpot) {
        this.parkingSpots.remove(parkingSpot);
    }

    public abstract int getRequiredSlots();

    public abstract boolean canFitInSlot(ParkingSpot slot);
}
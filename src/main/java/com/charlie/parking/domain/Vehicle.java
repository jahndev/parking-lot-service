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
    private VehicleSize size;

    @ManyToMany
    @JoinTable(name = "vehicle_parking_slot",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_slot_id"))
    private List<ParkingSlot> parkingSlots = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(String licensePlate, VehicleSize size) {
        this.licensePlate = licensePlate;
        this.size = size;
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

    public VehicleSize getSize() {
        return size;
    }

    public void setSize(VehicleSize size) {
        this.size = size;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public void addParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlots.add(parkingSlot);
    }

    public void removeParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlots.remove(parkingSlot);
    }

    public abstract int getRequiredSlots();

    public abstract boolean canFitInSlot(ParkingSlot slot);
}
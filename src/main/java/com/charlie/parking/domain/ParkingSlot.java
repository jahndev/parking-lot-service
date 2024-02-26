package com.charlie.parking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int lane;

    @Column(nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleSize size;

    @ManyToMany(mappedBy = "parkingSlots")
    private List<Vehicle> vehicles = new ArrayList<>();

    public ParkingSlot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void setSize(VehicleSize size) {
        this.size = size;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    public boolean isAvailable() {
        return this.vehicles.isEmpty();
    }
}

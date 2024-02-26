package com.charlie.parking.model;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {

    public ParkingSpot(Long id, ParkingSpotType parkingSpotType, Vehicle vehicle) {
        this.id = id;
        this.parkingSpotType = parkingSpotType;
        this.vehicle = vehicle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSpotType parkingSpotType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void unsetVehicle() {
        this.vehicle = null;
    }

    public ParkingSpot() {
    }

    public ParkingSpot(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }
}

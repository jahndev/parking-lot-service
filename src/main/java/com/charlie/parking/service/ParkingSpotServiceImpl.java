package com.charlie.parking.service;

import com.charlie.parking.model.*;
import com.charlie.parking.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;
    @Autowired
    public ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    // Implement the methods from the ParkingSpotService interface
    @Override
    public List<ParkingSpot> parkVehicle(Vehicle vehicle) {
        // Logic to park the vehicle
        List<ParkingSpot> parkingSpotList = parkingSpotRepository.findByVehicleLicensePlate(vehicle.getLicensePlate());
        if(!parkingSpotList.isEmpty()) {
            throw new IllegalArgumentException("Vehicle already exist " + vehicle.getLicensePlate());
        }

        Set<ParkingSpotType> compatibleSpotTypesByVehicleType = ParkingSpotType.getByVehicleType(vehicle.getVehicleType());

        List<ParkingSpot> availableSpots = parkingSpotRepository
                .findAvailableSlotsByVehicleType(compatibleSpotTypesByVehicleType)
                .stream()
                .limit(vehicle.getRequiredSlots())
                .collect(Collectors.toList());

        if(availableSpots.isEmpty()) {
            throw new IllegalArgumentException("spots hasn't been created, first create spots of type "
                    + compatibleSpotTypesByVehicleType);
        }

        if(availableSpots.size() == vehicle.getRequiredSlots()) {
            for (ParkingSpot spot : availableSpots) {
                spot.setVehicle(vehicle);
            }
            parkingSpotRepository.saveAll(availableSpots);
        } else {
            throw new IllegalArgumentException("Cannot park vehicle, not enough free slots "
                    + vehicle.getLicensePlate());
        }
        return availableSpots;
    }

    @Override
    public List<ParkingSpot> vehicleLeaves(Vehicle vehicle) {
        List<ParkingSpot> assignedSpots = parkingSpotRepository.findByVehicleLicensePlate(vehicle.getLicensePlate());
        if(assignedSpots.isEmpty()) {
            throw new IllegalArgumentException("Invalid vehicle, not present: " + vehicle.getLicensePlate());
        }
        assignedSpots.forEach(ParkingSpot::unsetVehicle);
        parkingSpotRepository.saveAll(assignedSpots);
        return assignedSpots;
    }

    @Override
    public Map<ParkingSpotType,Long> getRemainingSpots() {
        return parkingSpotRepository.getCountsByParkingSpotTypes();
    }

    @Override
    public boolean allSpotsTakenForType(VehicleType vehicleType) {
        List<ParkingSpot> allSpots = parkingSpotRepository.findAll();

        List<ParkingSpot> spotsByVehicleType = allSpots
                .stream().filter(
                        parkingSpot ->
                                parkingSpot.getVehicle() != null &&
                                parkingSpot.getVehicle().getVehicleType().equals(vehicleType)
                ).toList();

        return allSpots.size() == spotsByVehicleType.size();
    }
    public List<ParkingSpot> createParkingSpotsByType(ParkingSpotType parkingSpotType, Integer count) {
        // Check if the total number of parking spots exceeds the limit (25)
        if (parkingSpotRepository.findAll().size() + count > 25) {
            throw new IllegalStateException("Parking lot is full, cannot create more spots due exceeds 25.");
        }
        // Save the parking spot
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            parkingSpotList.add(new ParkingSpot(parkingSpotType));
        }
        return parkingSpotRepository.saveAll(parkingSpotList);
    }
}

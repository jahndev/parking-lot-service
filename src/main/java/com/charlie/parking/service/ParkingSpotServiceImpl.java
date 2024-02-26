package com.charlie.parking.service;

import com.charlie.parking.domain.*;
import com.charlie.parking.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository,
                                  VehicleRepository vehicleRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // Implement the methods from the ParkingSpotService interface
    @Override
    public List<ParkingSpot> parkVehicle(Vehicle vehicle) {
        // Logic to park the vehicle
        List<ParkingSpot> parkingSpotList = parkingSpotRepository.findByVehicleLicensePlate(vehicle.getLicensePlate());
        if(!parkingSpotList.isEmpty()) {
            throw new IllegalArgumentException("Vehicle already exist" + vehicle.getLicensePlate());
        }

        Set<ParkingSpotType> compatibleSpotTypesByVehicleType = ParkingSpotType.getByVehicleType(vehicle.getType());

        List<ParkingSpot> availableSpots = parkingSpotRepository
                .findAvailableSlotsByVehicleType(compatibleSpotTypesByVehicleType)
                .stream()
                .limit(vehicle.getRequiredSlots())
                .collect(Collectors.toList());

        if(availableSpots.size() == vehicle.getRequiredSlots()) {
            availableSpots
                    .stream()
                    .forEach(spot -> spot.setVehicle(vehicle));

            parkingSpotRepository.saveAll(availableSpots);
        } else {
            throw new IllegalArgumentException("Cannot park vehicle, not enough free slots "
                    + vehicle.getLicensePlate());
        }
        return availableSpots;
    }

    @Override
    public void vehicleLeaves(Vehicle vehicle) {
        List<ParkingSpot> assignedSpots = parkingSpotRepository.findByVehicleLicensePlate(vehicle.getLicensePlate());
        if(assignedSpots.isEmpty()) {
            throw new IllegalArgumentException("Invalid vehicle, not present: " + vehicle.getLicensePlate());
        }
        assignedSpots.forEach(ParkingSpot::unsetVehicle);
        parkingSpotRepository.saveAll(assignedSpots);
    }

    @Override
    public Map<ParkingSpotType,Integer> getRemainingSpots() {
        return parkingSpotRepository.getCountsByParkingSpotTypes();
    }

    @Override
    public boolean allSpotsTakenForType(VehicleType vehicleType) {
        List<ParkingSpot> allSpots = parkingSpotRepository.findAll();

        List<ParkingSpot> spotsByVehicleType = allSpots
                .stream().filter(
                        parkingSpot -> parkingSpot.getVehicle().getType().equals(vehicleType)
                ).toList();

        return allSpots.size() == spotsByVehicleType.size();
    }
    public List<ParkingSpot> createParkingSpotsByType(ParkingSpotType parkingSpotType, Integer count) {
        // Check if the total number of parking spots exceeds the limit (25)
        if (parkingSpotRepository.count() + count >= 25) {
            throw new IllegalStateException("Parking lot is full. Cannot create more parking spots.");
        }
        // Save the parking spot
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            parkingSpotList.add(new ParkingSpot(parkingSpotType));
        }
        return parkingSpotRepository.saveAll(parkingSpotList);
    }
}

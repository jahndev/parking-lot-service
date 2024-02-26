package com.charlie.parking.controller;

import com.charlie.parking.domain.*;
import com.charlie.parking.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/parking-spots")
public class ParkingSpotController {
    private final ParkingSpotService parkingSpotService;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping("/{type}/{count}")
    public ResponseEntity<List<ParkingSpot>> createParkingSpotsByType(@PathVariable String type,
                                                                      @PathVariable Integer count) {
        // Logic to park a vehicle in the specified spot
        List<ParkingSpot> parkedSpotList = parkingSpotService.createParkingSpotsByType(
                ParkingSpotType.valueOf(type), count
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(parkedSpotList);
    }

    @PostMapping
    public ResponseEntity<List<ParkingSpot>> parkVehicle(@RequestBody Vehicle vehicle) {
        // Logic to park a vehicle in the specified spot
        List<ParkingSpot> parkedSpot = parkingSpotService.parkVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkedSpot);
    }

    @PostMapping("/{spotId}")
    public ResponseEntity<Void> vehicleLeaves(@PathVariable Vehicle vehicle) {
        // Logic to remove the vehicle from the specified spot
        parkingSpotService.vehicleLeaves(vehicle);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/remaining")
    public ResponseEntity<Map<ParkingSpotType, Integer>> remainingSpots() {
        // Logic to calculate and return the number of remaining spots by spotType
        Map<ParkingSpotType, Integer> remainingSpots = parkingSpotService.getRemainingSpots();
        return ResponseEntity.ok(remainingSpots);
    }

    @GetMapping("/all-taken/{vehicleType}")
    public ResponseEntity<Boolean> allSpotsTakenForType(@PathVariable VehicleType vehicleType) {
        // Logic to check if all spots of the given type are taken
        boolean allTaken = parkingSpotService.allSpotsTakenForType(vehicleType);
        return ResponseEntity.ok(allTaken);
    }
}


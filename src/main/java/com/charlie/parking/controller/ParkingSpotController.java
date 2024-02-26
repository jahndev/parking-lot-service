package com.charlie.parking.controller;

import com.charlie.parking.model.*;
import com.charlie.parking.service.*;
import com.fasterxml.jackson.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("parking")
public class ParkingSpotController {
    private final ParkingSpotService parkingSpotService;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping("/{type}/{count}")
    public ResponseEntity<ParkingSpotControllerResponse> createParkingSpotsByType(@PathVariable String type,
                                                                      @PathVariable Integer count) {
        List<ParkingSpot> spots = new ArrayList<>();

        try {
            spots = parkingSpotService.createParkingSpotsByType(ParkingSpotType.valueOf(type), count);
        }catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ParkingSpotControllerResponse.builder()
                            .message(e.getMessage())
                            .spotList(spots)
                            .build()
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ParkingSpotControllerResponse.builder()
                        .message("Spot" + (spots.size() > 1? "s":"") + " created.")
                        .spotList(spots)
                        .build()
        );
    }

    @PostMapping("/vehicle")
    public ResponseEntity<ParkingSpotControllerResponse> parkVehicle(@RequestBody Vehicle vehicle)
            throws JsonProcessingException {
        List<ParkingSpot> parkedSpot = new ArrayList<>();
        try {
            parkedSpot = parkingSpotService.parkVehicle(vehicle);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ParkingSpotControllerResponse.builder().message("Error: Vehicle cannot be parked, "+
                            e.getMessage())
                            .build()
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ParkingSpotControllerResponse.builder()
                        .message("Vehicle parked ")
                        .spotList(parkedSpot)
                        .build()
        );
    }

    @PutMapping("/vehicle-leaving")
    public ResponseEntity<ParkingSpotControllerResponse> vehicleLeaves(@RequestBody Vehicle vehicle) {
        List<ParkingSpot> spots = parkingSpotService.vehicleLeaves(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(
                ParkingSpotControllerResponse.builder()
                        .message("Vehicle leaves parking lot, licensePlate: "
                                + vehicle.getLicensePlate()
                                + ", Spots released: ")
                        .spotList(spots)
                        .build()
        );
    }

    @GetMapping("/remaining")
    public ResponseEntity<Map<ParkingSpotType, Long>> remainingSpots() {
        // Logic to calculate and return the number of remaining spots by spotType
        Map<ParkingSpotType, Long> remainingSpots = parkingSpotService.getRemainingSpots();
        return ResponseEntity.ok(remainingSpots);
    }

    @GetMapping("/all-taken/{vehicleType}")
    public ResponseEntity<Boolean> allSpotsTakenForType(@PathVariable VehicleType vehicleType) {
        // Logic to check if all spots of the given type are taken
        boolean allTaken = parkingSpotService.allSpotsTakenForType(vehicleType);
        return ResponseEntity.ok(allTaken);
    }
}


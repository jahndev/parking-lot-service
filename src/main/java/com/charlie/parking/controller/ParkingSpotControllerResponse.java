package com.charlie.parking.controller;

import com.charlie.parking.model.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ParkingSpotControllerResponse {

    private String message;
    private List<ParkingSpot> spotList;
}

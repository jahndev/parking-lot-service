package com.charlie.parking.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
public enum VehicleType {
    @JsonProperty("MOTORCYCLE")
    MOTORCYCLE("MOTORCYCLE"),
    @JsonProperty("CAR")
    CAR("CAR"),
    @JsonProperty("VAN")
    VAN("VAN");

    private String name;

    private VehicleType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
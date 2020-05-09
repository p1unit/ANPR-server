package com.anpr.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VehicleType {
    @JsonProperty("BUS")
    BUS,
    @JsonProperty("CAR")
    CAR,
    @JsonProperty("TWO_WHEELER")
    TWO_WHEELER,
    @JsonProperty("TRUCK")
    TRUCK,
    @JsonProperty("OTHER")
    OTHER;
}

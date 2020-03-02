package com.licenseplatedetector.licenseplatedetector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VehicleType {
    @JsonProperty("Bus")
    BUS,
    @JsonProperty("Car")
    CAR,
    @JsonProperty("Two Wheeler")
    TWO_WHEELER,
    @JsonProperty("Truck")
    TRUCK,
    @JsonProperty("Other")
    OTHER;
}

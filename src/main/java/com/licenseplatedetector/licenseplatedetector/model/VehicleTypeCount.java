package com.licenseplatedetector.licenseplatedetector.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class VehicleTypeCount {

    private VehicleType type;
    private Long count;

    public VehicleTypeCount(Long count,VehicleType type) {
        this.type = type;
        this.count = count;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "VehicleTypeCount{" +
                "type=" + type +
                ", count=" + count +
                '}';
    }
}
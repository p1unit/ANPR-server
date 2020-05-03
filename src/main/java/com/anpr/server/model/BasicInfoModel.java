package com.anpr.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoModel {

    private int currentlyInside;
    private int todaysTotalCount;
    private List<String> mostFrequent;
    private List<VehicleTypeCount> typeCountList;
    private List<VehicleTypeCount> insideTypeCountList;

    @Override
    public String toString() {
        return "BasicInfoModel{" +
                "currentlyInside=" + currentlyInside +
                ", todaysTotalCount=" + todaysTotalCount +
                ", mostFrequent=" + mostFrequent +
                ", typeCountList=" + typeCountList +
                '}';
    }
}
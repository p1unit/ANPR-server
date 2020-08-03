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
    private int last1DayVisitor;
    private int last7DayVisitor;
    private int last30DayVisitor;
    private List<VehicleTypeCount> typeCountList;
    private List<VehicleTypeCount> insideTypeCountList;

    @Override
    public String toString() {
        return "BasicInfoModel{" +
                "currentlyInside=" + currentlyInside +
                ", last1DayVisitor=" + last1DayVisitor +
                ", last7DayVisitor=" + last7DayVisitor +
                ", last30DayVisitor=" + last30DayVisitor +
                ", typeCountList=" + typeCountList +
                ", insideTypeCountList=" + insideTypeCountList +
                '}';
    }
}
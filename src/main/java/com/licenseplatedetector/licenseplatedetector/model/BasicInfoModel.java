package com.licenseplatedetector.licenseplatedetector.model;

import java.util.List;

public class BasicInfoModel {

    private int currentlyInside;
    private int todaysTotalCount;
    private List<String> mostFrequent;
    private List<VehicleTypeCount> typeCountList;

    public int getCurrentlyInside() {
        return currentlyInside;
    }

    public void setCurrentlyInside(int currentlyInside) {
        this.currentlyInside = currentlyInside;
    }

    public int getTodaysTotalCount() {
        return todaysTotalCount;
    }

    public void setTodaysTotalCount(int todaysTotalCount) {
        this.todaysTotalCount = todaysTotalCount;
    }

    public List<String> getMostFrequent() {
        return mostFrequent;
    }

    public void setMostFrequent(List<String> mostFrequent) {
        this.mostFrequent = mostFrequent;
    }

    public List<VehicleTypeCount> getTypeCountList() {
        return typeCountList;
    }

    public void setTypeCountList(List<VehicleTypeCount> typeCountList) {
        this.typeCountList = typeCountList;
    }

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
package com.example.charging_app_homepage;

import java.time.LocalTime;

public class UsageRecords {
    private LocalTime startTime;
    private LocalTime endTime;
    private double batteryUsed;
    private double carbonSavings;

    public UsageRecords(LocalTime startTime, LocalTime endTime, double batteryUsed, double carbonSavings) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.batteryUsed = batteryUsed;
        this.carbonSavings = carbonSavings;
    }

    // Getters for each field
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getBatteryUsed() {
        return batteryUsed;
    }

    public double getCarbonSavings() {
        return carbonSavings;
    }

    @Override
    public String toString() {
        return "UsageRecord{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", batteryUsed=" + batteryUsed +
                ", carbonSavings=" + carbonSavings +
                '}';
    }
}

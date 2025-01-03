package com.example.charging_app_homepage;

import java.time.LocalTime;
import java.util.ArrayList;

public class ChargingSession {
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isActive;
    private double batteryStatus;
    private double carbonSavings;
    private int timeRemaining; // Time remaining in minutes
    private final double AVG_CO2_PER_KWH = 0.38; // kg CO2 per kWh
    private final double CO2_SAVED_PER_KWH = 2.31; // kg CO2 saved per kWh (from gasoline)
    private ArrayList<UsageRecords> usageRecords; // Store charging history

    // Constructor
    public ChargingSession(LocalTime startTime) {
        this.startTime = startTime;
        this.endTime = null;
        this.isActive = false;
        this.batteryStatus = 30.0; // Initial battery charge for demonstration purposes
        this.carbonSavings = 0.0;
        this.timeRemaining = 15; // Set initial time remaining to 15 minutes
        this.usageRecords = new ArrayList<>(); // Initialize the usage records list
    }

    // Getters and Setters
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public double getBatteryStatus() {
        return batteryStatus;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public double getCarbonSavings() {
        return carbonSavings;
    }

    // Method to start the charging session
    public void startSession() {
        this.isActive = true;
        this.startTime = LocalTime.now(); // Update start time
    }

    // Method to stop the charging session
    public void endSession() {
        this.isActive = false;
        this.endTime = LocalTime.now(); // Update end time when session stops
        UsageRecords record = new UsageRecords(startTime, endTime, batteryStatus, carbonSavings);
        usageRecords.add(record); // Add record to the list
    }

    // Method to update the battery status (charge increase per second)
    public void updateBatteryStatus(double increase) {
        if (batteryStatus < 100) {
            batteryStatus = Math.min(100.0, batteryStatus + increase);
        }
    }

    // Method to update carbon savings (based on kWh used)
    public void updateCarbonSavings(double kWhUsed) {
        this.carbonSavings += kWhUsed * AVG_CO2_PER_KWH; // Calculate CO2 savings
    }

    // Method to decrease time remaining (one minute per second)
    public void decreaseTimeRemaining() {
        if (timeRemaining > 0 && batteryStatus < 100) {
            timeRemaining--;
        }
    }

    // Method to calculate the amount of time that has passed since the session started
    public void calculateChargingTime() {
        if (isActive) {
            timeRemaining = Math.max(0, timeRemaining);
        }
    }

    // Method to check if the battery has reached 100% or the time is over
    public boolean isChargingComplete() {
        return batteryStatus >= 100 || timeRemaining <= 0;
    }

    // Method to simulate charging rate (10% increase per second for demonstration)
    public void simulateChargingRate() {
        if (isActive && batteryStatus < 100) {
            updateBatteryStatus(10.0);  // Increase by 10% per second
            updateCarbonSavings(0.1);   // Assume 0.1 kWh used per second for calculation
            decreaseTimeRemaining();    // Decrease the time by 1 minute per second
        }
    }

    // Getter for usageRecords to ensure it doesn't get deleted
    public ArrayList<UsageRecords> getUsageRecords() {
        return usageRecords;
    }
}

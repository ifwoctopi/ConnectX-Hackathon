package com.example.charging_app_homepage;

public class UserProfile {
    private String name;
    private double balance;
    private double carbonSavings;

    // Getters and setters for profile information
    public void addToBalance(double amount) {
        balance += amount;
    }

    public void addCarbonSavings(double savings) {
        carbonSavings += savings;
    }

    // Other profile-related methods...
}

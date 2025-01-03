package com.example.charging_app_homepage;

public class CurrentUser {
    // Declare a private static variable to hold the singleton instance
    private static CurrentUser instance;

    // The currently logged-in user
    private Profile loggedInUser;

    // Private constructor to prevent instantiation from outside
    private CurrentUser() {}

    // Public method to get the singleton instance
    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    // Set the logged-in user
    public void setLoggedInUser(Profile user) {
        this.loggedInUser = user;
    }

    // Get the logged-in user
    public Profile getLoggedInUser() {
        return this.loggedInUser;
    }
}


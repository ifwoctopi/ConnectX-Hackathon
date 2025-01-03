package com.example.charging_app_homepage;

public class UserSession {
    private static Profile currentUser; // Holds the logged-in user's profile

    public static Profile getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Profile profile) {
        currentUser = profile;
    }

    // Optionally, clear the current user when they log out
    public static void clear() {
        currentUser = null;
    }
}
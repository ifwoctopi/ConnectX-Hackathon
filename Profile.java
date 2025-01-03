package com.example.charging_app_homepage;

public class Profile {
    private String username, password, firstName, lastName, carMake, carModel;
    private int emissionsSaved, numCharges, rewardPoints;


    public Profile() {
        this.username = "null";
        this.password = "null";
        this.firstName = "null";
        this.lastName = "null";
        this.carMake = "null";
        this.carModel = "null";
        this.emissionsSaved = 0;
        this.numCharges = 0;
        this.rewardPoints = 0;
    }

    public Profile(String username, String password, String firstName, String lastName,
                   String carMake, String carModel, int emissionsSaved, int numCharges, int rewardPoints) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carMake = carMake;
        this.carModel = carModel;
        this.emissionsSaved = emissionsSaved;
        this.numCharges = numCharges;
        this.rewardPoints = rewardPoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getEmissionsSaved() {
        return emissionsSaved;
    }

    public void setEmissionsSaved(int emissionsSaved) {
        this.emissionsSaved = emissionsSaved;
    }

    public int getNumCharges() {
        return numCharges;
    }

    public void setNumCharges(int numCharges) {
        this.numCharges = numCharges;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    // Override toString() for a readable representation of Profile objects

    @Override
    public String toString() {
        return "CarOwner{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", carMake='" + carMake + '\'' +
                ", carModel='" + carModel + '\'' +
                ", batteryCapacity=" + emissionsSaved + '\'' +
                ", milesDriven=" + numCharges + '\'' +
                ", Reward Points=" + rewardPoints + '\'' +
                '}';
    }
}


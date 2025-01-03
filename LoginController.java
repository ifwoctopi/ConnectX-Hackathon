package com.example.charging_app_homepage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Button login_button;


    private List<Profile> profiles = new ArrayList<>();
    public static  Profile current_user;

    // Initialize method to load CSV data when the controller is created
    @FXML
    public void initialize() {
        populateCarOwnersFromCSV("src/main/resources/com/example/charging_app_homepage/users.csv", profiles);
    }

    /**
     * Reads profiles from a CSV file and loads them into a list.
     * @param csvFile The path to the CSV file.
     * @param profiles The list to populate with Profile objects.
     */
    public static void populateCarOwnersFromCSV(String csvFile, List<Profile> profiles) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            // Skip the header if it exists
            //br.readLine();

            // Continue reading lines while there is data
            while ((line = br.readLine()) != null) {
                // Split each line by commas
                String[] data = line.split(",");

                // Ensure the line has the expected number of fields
                if (data.length < 9) {
                    System.err.println("Skipping incomplete line: " + line);
                    continue;
                }

                try {
                    // Extract values and parse integer fields
                    String username = data[0].trim();
                    String password = data[1].trim();
                    String firstName = data[2].trim();
                    String lastName = data[3].trim();
                    String carMake = data[4].trim();
                    String carModel = data[5].trim();
                    int emissionsSaved = Integer.parseInt(data[6].trim());
                    int numCharges = Integer.parseInt(data[7].trim());
                    int rewardPoints = Integer.parseInt(data[8].trim());

                    // Create a new Profile object
                    Profile p = new Profile(username, password, firstName, lastName, carMake, carModel, emissionsSaved, numCharges, rewardPoints);

                    // Add the Profile object to the list
                    profiles.add(p);

                } catch (NumberFormatException e) {
                    System.err.println("Skipping line due to number format error: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the login action.
     * @param event The action event triggered by clicking the Login button.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String enteredUsername = Username.getText();
        String enteredPassword = Password.getText();

        // Validate the login credentials
        Profile userProfile = validateLogin(enteredUsername, enteredPassword);

        if (userProfile != null) {
            // Store the logged-in user in the shared UserSession
            current_user = userProfile;

            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + enteredUsername + "!");
            loadHomePage();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    /**
     * Validates the entered username and password against the loaded profiles.
     * @param username The entered username.
     * @param password The entered password.
     * @return true if credentials match a profile, otherwise false.
     */
    private Profile validateLogin(String username, String password) {
        for (Profile profile : profiles) {
            if (profile.getUsername().equals(username) && profile.getPassword().equals(password)) {
                return profile;  // Return the matching profile
            }
        }
        return null;  // Return null if no match found
    }

    /**
     * Displays an alert with a specified title and message.
     * @param alertType The type of alert (e.g., INFORMATION, ERROR).
     * @param title The title of the alert dialog.
     * @param message The message content of the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadHomePage() {
        try {
            // Load the new FXML file for the home page (no need to reference AnchorPane here)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene homeScene = new Scene(loader.load());  // Load the layout directly into the scene

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) Username.getScene().getWindow();
            stage.setScene(homeScene);  // Change the scene to the home page
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the home page.");
        }
    }
}



package com.example.charging_app_homepage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;

public class ChargerController {

    @FXML
    private Button HomeButton;

    @FXML
    private Button startChargingButton;

    @FXML
    private Button stopChargingButton;

    @FXML
    private Text charging_boolean;

    @FXML
    private Text battery_percentage;

    @FXML
    private ImageView battery_image;

    @FXML
    private Text time_remaining_analog;

    @FXML
    private Text time_remaining_words;

    private boolean isCharging = false;
    private Timeline timeline;
    public static ChargingSession chargingSession; // Declare the charging session variable
    private double timeRemainingInMinutes = 15.0; // Total time for the session in minutes

    @FXML
    private void startCharging() {
        if (!isCharging) {
            isCharging = true;
            startChargingButton.setDisable(true);
            stopChargingButton.setDisable(false);

            // Initialize a new charging session or continue the existing one
            if (chargingSession == null || !chargingSession.isActive()) {
                chargingSession = new ChargingSession(LocalTime.now());
            }

            chargingSession.startSession(); // Set the start time

            // Update the charging status on UI
            charging_boolean.setText("Charging...");
            battery_percentage.setText(String.format("%.0f%%", chargingSession.getBatteryStatus()));


            // Set up a timeline to update the battery and carbon savings
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateCharging()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }
    @FXML
    private void loadHomePage(ActionEvent event) {
        try {
            // Create the FXMLLoader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

            // Load the home page FXML
            Pane homePage = loader.load();

            // Get the controller for the home page
            HomePageController controller = loader.getController();

            // Pass the logged-in user to the HomePageController
            Profile loggedInUser = UserSession.getCurrentUser(); // Assuming you have set the current user in UserSession
            controller.setLoggedInUser();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) HomeButton.getScene().getWindow();
            Scene homeScene = new Scene(homePage);
            stage.setScene(homeScene);  // Change the scene to the home page
            stage.show();  // Show the new scene

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void stopCharging() {
        if (isCharging) {
            isCharging = false;
            timeline.stop();
            stopChargingButton.setDisable(true);
            startChargingButton.setDisable(false);
            charging_boolean.setText("Charging Stopped");

            // End the session and update the charging summary
            chargingSession.endSession(); // Set the end time
            showChargingSummary();
        }
    }

    private void updateCharging() {
        // Simulate battery increase by 10% per second (accelerated)
        chargingSession.updateBatteryStatus(10.0); // Increase battery by 10% every second
        battery_percentage.setText(String.format("%.0f%%", chargingSession.getBatteryStatus()));

        // Simulate carbon savings update
        chargingSession.updateCarbonSavings(0.1); // Increase carbon savings (0.1 kg per second for demonstration)

        // Update UI with carbon savings
        double co2Saved = chargingSession.getCarbonSavings();
        time_remaining_analog.setText(String.format("CO2 Saved: %.2f kg", co2Saved));

        // Update the minutes remaining, decreasing at a rate of 1 minute per second
        if (timeRemainingInMinutes > 0) {
            timeRemainingInMinutes -= 1.0;
        }
        time_remaining_words.setText(String.format("%.0f Mins", timeRemainingInMinutes));

        // Check if the battery is almost full (90% or more)
        if (chargingSession.getBatteryStatus() >= 90) {
            showAlert("Battery Almost Full", "Your battery is almost fully charged!");
        }

        // Check if the charging time is almost over (1 minute remaining)
        if (timeRemainingInMinutes <= 1) { // Show alert when there's 1 minute left
            showAlert("Charging Time Almost Over", "Your charging time is almost finished!");
        }

        // If the battery reaches 100%, stop charging
        if (chargingSession.getBatteryStatus() >= 100) {
            stopCharging();
        }

        // If the time runs out, stop charging
        if (timeRemainingInMinutes <= 0) {
            stopCharging();
        }
    }

    private void showChargingSummary() {
        double timeCharged = chargingSession.getTimeRemaining(); // Time charged can be derived from remaining time
        double batteryCharged = chargingSession.getBatteryStatus();
        double carbonSaved = chargingSession.getCarbonSavings();

        // Here, you can show a summary (e.g., in a dialog or in a text box)
        System.out.println("Charging Session Summary:");
        System.out.println("Time Charged: " + timeCharged + " minutes");
        System.out.println("Battery Charged: " + batteryCharged + "%");
        System.out.println("Carbon Savings: " + carbonSaved + " kg CO2");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional: Can be used for a header
        alert.setContentText(message);

        // Show the alert dialog
        alert.showAndWait();
    }
}

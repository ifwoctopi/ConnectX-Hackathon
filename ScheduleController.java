package com.example.charging_app_homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField hourField;
    @FXML
    private TextField minuteField;
    @FXML
    private Button schedule;
    @FXML
    private Button charging;
    @FXML
    private Button history;
    @FXML
    private Button your_car;

    @FXML
    private Button scheduleConfirmButton, cancelButton;

    private String selectedTimeSlot;

    // Event handler for the time buttons
    @FXML
    private void handleTimeButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        selectedTimeSlot = clickedButton.getText();
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
            Stage stage = (Stage) schedule.getScene().getWindow();
            Scene homeScene = new Scene(homePage);
            stage.setScene(homeScene);  // Change the scene to the home page
            stage.show();  // Show the new scene

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void loadChargingPage(ActionEvent event) {
        try {
            // Load the FXML file for the charging page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("charging_page.fxml"));
            Pane chargingPage = loader.load();  // Load the layout

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) charging.getScene().getWindow();
            Scene chargingScene = new Scene(chargingPage);
            stage.setScene(chargingScene);  // Change the scene to the charging page
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an error alert
        }
    }
    @FXML
    private void loadHistoryPage(ActionEvent event) {
        try {
            // Load the FXML file for the history page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history_page.fxml"));
            Pane historyPage = loader.load();  // Load the layout

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) history.getScene().getWindow();
            Scene historyScene = new Scene(historyPage);
            stage.setScene(historyScene);  // Change the scene to the history page
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an error alert
        }
    }


    // Confirm scheduling action
    @FXML
    private void handleConfirmAction(ActionEvent event) {
        // Get the selected date and time duration
        String selectedDate = datePicker.getValue().toString();
        String selectedDuration = hourField.getText() + " hours " + minuteField.getText() + " minutes";

        // Show success pop-up
        showSuccessPopup();

        // Launch ChargerApplication after confirmation
        launchChargerApplication();
    }

    // Cancel the scheduling action
    @FXML
    private void handleCancelAction(ActionEvent event) {
        // Clear fields or reset the state if necessary
        datePicker.setValue(null);
        hourField.clear();
        minuteField.clear();
    }

    // Function to show a success pop-up
    private void showSuccessPopup() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Timeslot scheduled successfully!");
        alert.showAndWait();
    }

    // Function to launch the ChargerApplication after confirmation
    private void launchChargerApplication() {
        try {
            ChargerApplication chargerApp = new ChargerApplication();
            Stage stage = new Stage();
            chargerApp.start(stage);  // Open the new window for ChargerApplication
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

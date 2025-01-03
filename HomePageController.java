package com.example.charging_app_homepage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import static com.example.charging_app_homepage.ChargerController.chargingSession;
import static com.example.charging_app_homepage.LoginController.current_user;

public class HomePageController {

    @FXML
    private Button HomeButton;

    @FXML
    private Text Time_Remaining;

    @FXML
    private Text session_number;

    @FXML
    private Text battery_lvl1;

    @FXML
    private Text battery_lvl2;

    @FXML
    private Pane batterylevel1;

    @FXML
    private Text bottom_panel;

    @FXML
    private Text car;

    @FXML
    private Button charger_button;

    @FXML
    private Button history_button;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane most_inner_pane;

    @FXML
    private Pane pane_l;

    @FXML
    private Pane pane_l1;

    @FXML
    private Pane pane_l2;

    @FXML
    private Pane pane_l21;

    @FXML
    private Pane pane_l3;

    @FXML
    private Text points;

    @FXML
    private HBox root;

    @FXML
    private Text saved_emissions;

    @FXML
    private Button schedule_button;
    @FXML
    private Label welcome_text;

    private Profile loggedInUser;

    @FXML
    private Text messageDisplay; // Text element in FXML for displaying messages

    private ArrayList<String> messages = new ArrayList<>();
    private Random random = new Random(); // Initialize Random object
    //Profile loggedInUser = UserSession.getCurrentUser();


    public void initialize() {
        System.out.println("Initializing profile...");
        initializeProfile();  // Call the profile initialization method
        // Populate the ArrayList with messages
        messages.add("Electric cars’ share of the car market has increased from 4 percent in 2020 to 14 percent in 2022. ");
        messages.add("An EV would cost only $1.22 in energy to travel the same distance as a gallon of gas, which currently averages $3.83. ");
        messages.add("Reducing emissions can boost biodiversity—cleaner air helps plants and wildlife thrive, creating healthier ecosystems.");
        messages.add("One tree absorbs around 48 pounds of CO₂ per year—every ton of emissions saved is like planting over 40 trees!");
        messages.add("A typical gasoline car emits over 4.6 metric tons of CO₂ per year—driving an electric car can cut that down significantly.");

        // Display a random message
        startMessageRotation();
    }

    public void initializeProfile() {
         // Get the logged-in user

        // Populate the UI components with the user's details
        if (current_user != null) {
            welcome_text.setText("Hello, " + current_user.getFirstName() + " " + current_user.getLastName());
            saved_emissions.setText(current_user.getEmissionsSaved() + " KT");
            car.setText(current_user.getCarMake() + " " + current_user.getCarModel());
            points.setText(String.valueOf(current_user.getRewardPoints()));

            if (chargingSession == null || !chargingSession.isActive()) {
                chargingSession = new ChargingSession(LocalTime.now());
            }
            battery_lvl1.setText(String.format("%.0f%%", chargingSession.getBatteryStatus()));
            battery_lvl2.setText("Battery Level: " + String.format("%.0f%%", chargingSession.getBatteryStatus()));

            String sessionOrdinal = getOrdinal(current_user.getNumCharges());
            session_number.setText(sessionOrdinal);
        }
    }
    public void setLoggedInUser() {
        initializeProfile(); // Populate the profile UI with the user information
    }

    private String getOrdinal(int number) {
        // Handle special cases for 11th, 12th, 13th, etc.
        if (number % 100 >= 11 && number % 100 <= 13) {
            return number + "th";
        }

        // Handle general cases
        switch (number % 10) {
            case 1: return number + "st";
            case 2: return number + "nd";
            case 3: return number + "rd";
            default: return 1 + "st";
        }
    }
    @FXML
    private void loadSchedulePage(ActionEvent event) {
        try {
            // Load the FXML file for the schedule page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("schedule_page.fxml"));
            Pane schedulePage = loader.load();  // Load the layout

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) schedule_button.getScene().getWindow();
            Scene scheduleScene = new Scene(schedulePage);
            stage.setScene(scheduleScene);  // Change the scene to the schedule page
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an error alert
        }
    }

    // Method to load the Charging page
    @FXML
    private void loadChargingPage(ActionEvent event) {
        try {
            // Load the FXML file for the charging page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("charging_page.fxml"));
            Pane chargingPage = loader.load();  // Load the layout

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) charger_button.getScene().getWindow();
            Scene chargingScene = new Scene(chargingPage);
            stage.setScene(chargingScene);  // Change the scene to the charging page
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an error alert
        }
    }

    // Method to load the History page
    @FXML
    private void loadHistoryPage(ActionEvent event) {
        try {
            // Load the FXML file for the history page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history_page.fxml"));
            Pane historyPage = loader.load();  // Load the layout

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) history_button.getScene().getWindow();
            Scene historyScene = new Scene(historyPage);
            stage.setScene(historyScene);  // Change the scene to the history page
            stage.show();  // Show the new scene
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an error alert
        }
    }

    // Other methods...

 






    private void displayRandomMessage() {
        String randomMessage = messages.get(random.nextInt(messages.size()));
        messageDisplay.setText(randomMessage);
    }

    private void startMessageRotation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> displayRandomMessage()) // Update every 5 seconds
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }


}

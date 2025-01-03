package com.example.charging_app_homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.List;
import javafx.scene.control.Button;

public class HistoryController {

    @FXML
    private ListView<String> usageListView;

    @FXML
    private Button home_page;

    // Sample data for UsageRecords
    private List<UsageRecords> usageRecordsList = List.of(
            new UsageRecords(LocalTime.of(9, 0), LocalTime.of(10, 0), 10.5, 2.3),
            new UsageRecords(LocalTime.of(10, 0), LocalTime.of(11, 0), 8.2, 1.8),
            new UsageRecords(LocalTime.of(11, 0), LocalTime.of(12, 0), 15.0, 3.1)
    );

    // Method to initialize the ListView
    public void initialize() {
        // Clear any previous items in the ListView
        usageListView.getItems().clear();

        // Set the font for the ListView items
        usageListView.setStyle("-fx-font-size: 18px;");

        // Populate ListView with formatted UsageRecord data
        for (UsageRecords record : usageRecordsList) {
            usageListView.getItems().add(record.toString());
        }
    }@FXML
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
            Stage stage = (Stage) home_page.getScene().getWindow();
            Scene homeScene = new Scene(homePage);
            stage.setScene(homeScene);  // Change the scene to the home page
            stage.show();  // Show the new scene

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.ui;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import com.example.service.ApiService;
import com.example.model.DroneDTO;
import javafx.scene.layout.HBox;
import com.example.controller.DroneController;
import javafx.application.Platform;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class MainView extends VBox {
    private final ApiService apiService;
    private final TableView<DroneDTO> droneTable;
    private final DroneController droneController;
    private final TextArea statusArea;

    public MainView() {
        this.apiService = new ApiService();
        this.droneTable = new TableView<>();
        this.droneController = new DroneController();
        this.statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.setPrefRowCount(5);

        // Create table columns
        TableColumn<DroneDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> javafx.beans.property.SimpleIntegerProperty(
            data.getValue().getId()).asObject());

        TableColumn<DroneDTO, String> serialColumn = new TableColumn<>("Serial Number");
        serialColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getSerialnumber()));

        TableColumn<DroneDTO, String> typeColumn = new TableColumn<>("Carriage Type");
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getCarriageType()));

        TableColumn<DroneDTO, Integer> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(data -> javafx.beans.property.SimpleIntegerProperty(
            data.getValue().getCarriageWeight()).asObject());

        droneTable.getColumns().addAll(idColumn, serialColumn, typeColumn, weightColumn);

        Button refreshButton = new Button("Refresh Drones");
        refreshButton.setOnAction(e -> updateDroneList());

        // Create direction controls
        HBox controls = new HBox(10);
        Button upButton = new Button("Forward");
        Button downButton = new Button("Backward");
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");

        upButton.setOnAction(e -> moveDrone("forward"));
        downButton.setOnAction(e -> moveDrone("backward"));
        leftButton.setOnAction(e -> moveDrone("left"));
        rightButton.setOnAction(e -> moveDrone("right"));

        controls.getChildren().addAll(upButton, downButton, leftButton, rightButton);

        // Create altitude slider
        Slider altitudeSlider = new Slider(0, 100, 0);
        altitudeSlider.setShowTickLabels(true);
        altitudeSlider.setShowTickMarks(true);
        altitudeSlider.valueProperty().addListener((obs, oldVal, newVal) -> 
            setAltitude(newVal.doubleValue()));

        setSpacing(10);
        getChildren().addAll(refreshButton, droneTable, controls, altitudeSlider, statusArea);

        // Setup automatic status updates
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateStatus()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Initial load
        updateDroneList();
    }

    private void moveDrone(String direction) {
        droneController.moveDrone(direction);
        updateStatus();
    }

    private void setAltitude(double altitude) {
        droneController.setAltitude(altitude);
        updateStatus();
    }

    private void updateStatus() {
        Platform.runLater(() -> 
            statusArea.setText(droneController.getDroneStatus())
        );
    }

    private void updateDroneList() {
        try {
            var drones = apiService.getDrones();
            droneTable.setItems(FXCollections.observableArrayList(drones));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading drones: " + e.getMessage())
                .showAndWait();
        }
    }
}

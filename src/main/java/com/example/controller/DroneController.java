package com.example.controller;

import com.example.model.Drone;

public class DroneController {
    private final Drone drone;
    private static final double MOVE_STEP = 1.0;

    public DroneController() {
        this.drone = new Drone();
    }

    public void moveDrone(String direction) {
        switch (direction.toLowerCase()) {
            case "forward" -> drone.setY(drone.getY() + MOVE_STEP);
            case "backward" -> drone.setY(drone.getY() - MOVE_STEP);
            case "left" -> drone.setX(drone.getX() - MOVE_STEP);
            case "right" -> drone.setX(drone.getX() + MOVE_STEP);
        }
        // Simulate battery usage
        drone.setBatteryLevel(Math.max(0, drone.getBatteryLevel() - 0.1));
    }

    public void setAltitude(double altitude) {
        drone.setAltitude(altitude);
        // Simulate battery usage
        drone.setBatteryLevel(Math.max(0, drone.getBatteryLevel() - 0.2));
    }

    public String getDroneStatus() {
        return String.format("""
            Position: (%.1f, %.1f)
            Altitude: %.1f m
            Battery: %.1f%%""",
            drone.getX(), drone.getY(),
            drone.getAltitude(),
            drone.getBatteryLevel());
    }
}

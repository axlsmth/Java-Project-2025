package com.example.model;

public class Drone {
    private double x;
    private double y;
    private double altitude;
    private double batteryLevel;

    public Drone() {
        this.x = 0;
        this.y = 0;
        this.altitude = 0;
        this.batteryLevel = 100;
    }

    // Getters and setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }
    public double getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(double batteryLevel) { this.batteryLevel = batteryLevel; }
}

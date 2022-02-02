package com.ibm.carpoolbuddy;
import java.util.*;

public class Vehicle {
    private String ownerID;
    private String ownerName;
    private String brand;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;
    private boolean open;
    private String vehicleType;
    private double basePrice;
    private String startingLocation;

    public Vehicle()
    {

    }

    public Vehicle(String ownerID, String ownerName, String brand, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String startingLocation) {
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.ridersUIDs = ridersUIDs;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
        this.startingLocation = startingLocation;
    }


    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String owner) {
        this.ownerID = ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public ArrayList<String> getRidersUIDs() {
        return ridersUIDs;
    }

    public void setRidersUID(ArrayList<String> ridersUIDs) {
        this.ridersUIDs = ridersUIDs;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    @Override
    public String toString() {
        return "Vehicle: \n" +
                "OwnerID = " + ownerID +
                "OwnerName = " + ownerName +
                "Brand = " + brand +
                ", Model = " + model +
                ", Capacity = " + capacity +
                ", Vehicle ID = " + vehicleID +
                ", Riders User IDs = " + ridersUIDs +
                ", Open = " + open +
                ", Vehicle Type = " + vehicleType +
                ", Base Price = " + basePrice +
                ", Starting Location = " + startingLocation;
    }
}

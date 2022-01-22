package com.ibm.carpoolbuddy;
import java.util.*;

public class Vehicle {
    private String ownerID;
    private String brand;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;
    private boolean open;
    private String vehicleType;
    private double basePrice;

    public Vehicle()
    {

    }

    public Vehicle(String ownerID, String brand, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice) {
        this.ownerID = ownerID;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.ridersUIDs = ridersUIDs;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String owner) {
        this.ownerID = ownerID;
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

    @Override
    public String toString() {
        return "Vehicle: \n" +
                "OwnerID = " + ownerID +
                "Brand = " + brand +
                ", Model = " + model +
                ", Capacity = " + capacity +
                ", Vehicle ID = " + vehicleID +
                ", Riders User IDs = " + ridersUIDs +
                ", Open = " + open +
                ", Vehicle Type = " + vehicleType +
                ", Base Price = " + basePrice;
    }
}

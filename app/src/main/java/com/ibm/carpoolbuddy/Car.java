package com.ibm.carpoolbuddy;
import java.util.*;

public class Car extends Vehicle{
    private int range;
    private int fuelCapacity;
    private String safetyRating;

    public Car()
    {

    }

    public Car(int range, int fuelCapacity, String safetyRating)
    {
        this.range = range;
        this.fuelCapacity = fuelCapacity;
        this.safetyRating = safetyRating;
    }

    public Car(String ownerID, String ownerName, String brand, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int range, int fuelCapacity, String safetyRating)
    {
        super(ownerID, ownerName, brand, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
        this.range = range;
        this.fuelCapacity = fuelCapacity;
        this.safetyRating = safetyRating;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getSafetyReview() {
        return safetyRating;
    }

    public void setSafetyReview(String safetyReview) {
        this.safetyRating = safetyReview;
    }

    @Override
    public String toString() {
        return "Vehicle: \n" +
                "OwnerID = " + getOwnerID() +
                "OwnerName = " + getOwnerName() +
                "Brand = " + getBrand() +
                ", Model = " + getModel() +
                ", Capacity = " + getCapacity() +
                ", Vehicle ID = " + getVehicleID() +
                ", Riders User IDs = " + getRidersUIDs() +
                ", Open = " + isOpen() +
                ", Vehicle Type = " + getVehicleType() +
                ", Base Price = " + getBasePrice() +
                ", Range = " + range +
                ", Fuel Capacity = " + fuelCapacity +
                ", Safety Review = " + safetyRating;
    }
}

package com.ibm.carpoolbuddy;
import java.util.*;

public class Car extends Vehicle{
    private int range;
    private int fuelCapacity;
    private String safetyReview;

    public Car()
    {

    }

    public Car(int range, int fuelCapacity, String safetyReview)
    {
        this.range = range;
        this.fuelCapacity = fuelCapacity;
        this.safetyReview = safetyReview;
    }

    public Car(String owner, String brand, String model, int capacity, String vehicleIDs, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int range, int fuelCapacity, String safetyReview)
    {
        super(owner, brand, model, capacity, vehicleIDs, ridersUIDs, open, vehicleType, basePrice);
        this.range = range;
        this.fuelCapacity = fuelCapacity;
        this.safetyReview = safetyReview;
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
        return safetyReview;
    }

    public void setSafetyReview(String safetyReview) {
        this.safetyReview = safetyReview;
    }

    @Override
    public String toString() {
        return "Vehicle: \n" +
                "Owner = " + getOwner() +
                "Brand = " + getBrand() +
                ", Model = " + getModel() +
                ", Capacity = " + getCapacity() +
                ", Vehicle IDs = " + getVehicleIDs() +
                ", Riders User IDs = " + getRidersUIDs() +
                ", Open = " + isOpen() +
                ", Vehicle Type = " + getVehicleType() +
                ", Base Price = " + getBasePrice() +
                ", Range = " + range +
                ", Fuel Capacity = " + fuelCapacity +
                ", Safety Review = " + safetyReview;
    }
}

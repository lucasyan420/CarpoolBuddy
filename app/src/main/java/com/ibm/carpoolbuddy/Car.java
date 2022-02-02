package com.ibm.carpoolbuddy;
import java.util.*;

public class Car extends Vehicle{
    private int range;
    private int fuelCapacity;

    public Car()
    {

    }

    public Car(int range, int fuelCapacity)
    {
        this.range = range;
        this.fuelCapacity = fuelCapacity;
    }

    public Car(String ownerID, String ownerName, String brand, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String startingLocation, int range, int fuelCapacity)
    {
        super(ownerID, ownerName, brand, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, startingLocation);
        this.range = range;
        this.fuelCapacity = fuelCapacity;
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
                ", Starting Location = " + getStartingLocation() +
                ", Range = " + range +
                ", Fuel Capacity = " + fuelCapacity;
    }
}

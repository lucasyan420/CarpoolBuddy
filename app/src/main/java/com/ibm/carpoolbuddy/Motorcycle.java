package com.ibm.carpoolbuddy;

import java.util.ArrayList;

public class Motorcycle extends Vehicle{
    private int weight;
    private int length;

    public Motorcycle()
    {

    }

    public Motorcycle(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    public Motorcycle(String ownerID, String ownerName, String brand, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String startingLocation, int weight, int length) {
        super(ownerID, ownerName, brand, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, startingLocation);
        this.weight = weight;
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
                ", Weight = " + weight +
                ", Length = " + length;
    }
}

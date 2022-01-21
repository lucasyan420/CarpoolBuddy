package com.ibm.carpoolbuddy;

import java.util.ArrayList;

public class Motorcycle extends Vehicle{
    private int weight;
    private int length;
    private String seatType;

    public Motorcycle()
    {

    }

    public Motorcycle(int weight, int length, String seatType) {
        this.seatType = seatType;
        this.weight = weight;
        this.length = length;
    }

    public Motorcycle(String ownerID, String brand, String model, int capacity, String vehicleIDs, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int weight, int length, String seatType) {
        super(ownerID, brand, model, capacity, vehicleIDs, ridersUIDs, open, vehicleType, basePrice);
        this.weight = weight;
        this.length = length;
        this.seatType = seatType;
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

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return "Vehicle: \n" +
                "OwnerID = " + getOwnerID() +
                "Brand = " + getBrand() +
                ", Model = " + getModel() +
                ", Capacity = " + getCapacity() +
                ", Vehicle IDs = " + getVehicleIDs() +
                ", Riders User IDs = " + getRidersUIDs() +
                ", Open = " + isOpen() +
                ", Vehicle Type = " + getVehicleType() +
                ", Base Price = " + getBasePrice() +
                ", Weight = " + weight +
                ", Length = " + length +
                ", Seat Type = " + seatType;
    }
}

package com.ibm.carpoolbuddy;

import java.util.ArrayList;

/**
 * This is the electric car class which creates the car object with its characteristics,
 * and includes constructor, getters, setters and toString. Extends the vehicle class
 */
public class ElectricCar extends Vehicle {
    private int batteryLife;
    private int chargingTime;

    public ElectricCar() {

    }

    public ElectricCar(int batteryLife, int chargingTime) {
        this.batteryLife = batteryLife;
        this.chargingTime = chargingTime;
    }

    public ElectricCar(String ownerID, String ownerName, String brand, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String startingLocation, int batteryLife, int chargingTime) {
        super(ownerID, ownerName, brand, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, startingLocation);
        this.batteryLife = batteryLife;
        this.chargingTime = chargingTime;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public int getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(int chargingTime) {
        this.chargingTime = chargingTime;
    }

    @Override
    public String toString() {
        return "Vehicle: \n" +
                "OwnerID = " + getOwnerID() +
                "OwnerName = " + getOwnerName() +
                "Brand = " + getBrand() +
                ", Model = " + getModel() +
                ", Capacity = " + getCapacity() +
                ", Vehicle IDs = " + getVehicleID() +
                ", Riders User IDs = " + getRidersUIDs() +
                ", Open = " + isOpen() +
                ", Vehicle Type = " + getVehicleType() +
                ", Base Price = " + getBasePrice() +
                ", Starting Location = " + getStartingLocation() +
                ", Battery Life = " + batteryLife +
                ", Charging Time = " + chargingTime;
    }
}

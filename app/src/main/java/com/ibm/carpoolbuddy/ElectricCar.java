package com.ibm.carpoolbuddy;

import java.util.ArrayList;

public class ElectricCar extends Vehicle{
    private int batteryLife;
    private int chargingTime;
    private String smartDriveFeatures;

    public ElectricCar()
    {

    }

    public ElectricCar(int batteryLife, int chargingTime, String smartDriveFeatures) {
        this.batteryLife = batteryLife;
        this.chargingTime = chargingTime;
        this.smartDriveFeatures = smartDriveFeatures;
    }

    public ElectricCar(String owner, String brand, String model, int capacity, String vehicleIDs, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int batteryLife, int chargingTime, String smartDriveFeatures) {
        super(owner, brand, model, capacity, vehicleIDs, ridersUIDs, open, vehicleType, basePrice);
        this.batteryLife = batteryLife;
        this.chargingTime = chargingTime;
        this.smartDriveFeatures = smartDriveFeatures;
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

    public String getSmartDriveFeatures() {
        return smartDriveFeatures;
    }

    public void setSmartDriveFeatures(String smartDriveFeatures) {
        this.smartDriveFeatures = smartDriveFeatures;
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
                ", Battery Life = " + batteryLife +
                ", Charging Time = " + chargingTime +
                ", Smart Drive Features = " + smartDriveFeatures;
    }
}

package com.ibm.carpoolbuddy;

import java.lang.reflect.Array;
import java.util.*;

/**
 * This class details the characteristics of the overarching user object which emcompasses the
 * student, teacher, parent and alumni class, and includes the constructors, getters, setters
 * and toString method
 */
public class User {
    private String uid;
    private String name;
    private String email;
    private String userType;
    private double priceMultiplier;
    ArrayList<String> ownedVehicles;
    ArrayList<String> bookedVehicles;
    private int environmentPoints;

    public User() {

    }

    public User(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> bookedVehicles, int environmentPoints) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.priceMultiplier = priceMultiplier;
        this.ownedVehicles = ownedVehicles;
        this.bookedVehicles = bookedVehicles;
        this.environmentPoints = environmentPoints;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public ArrayList<String> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(ArrayList<String> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }

    public ArrayList<String> getBookedVehicles() {
        return bookedVehicles;
    }

    public void setBookedVehicles(ArrayList<String> bookedVehicles) {
        this.bookedVehicles = bookedVehicles;
    }

    public int getEnvironmentPoints() {
        return environmentPoints;
    }

    public void setEnvironmentPoints(int environmentPoints) {
        this.environmentPoints = environmentPoints;
    }

    @Override
    public String toString() {
        return "User: \n" +
                "User Id = " + uid +
                ", Name = " + name +
                ", Email = " + email +
                ", User Type = " + userType +
                ", Price Multiplier = " + priceMultiplier +
                ", Owned Vehicles = " + ownedVehicles +
                ", Booked Vehicles = " + bookedVehicles;
    }
}

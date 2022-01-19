package com.ibm.carpoolbuddy;
import java.util.*;

public class User {
    private String uid;
    private String name;
    private String email;
    private String userType;
    private double priceMultiplier;
    ArrayList<String> ownedVehicles;

    public User()
    {

    }

    public User(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.priceMultiplier = priceMultiplier;
        this.ownedVehicles = ownedVehicles;
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

    @Override
    public String toString() {
        return "User: \n" +
                "User Id = " + uid +
                ", Name = " + name +
                ", Email = " + email +
                ", User Type = " + userType +
                ", Price Multiplier = " + priceMultiplier +
                ", Owned Vehicles = " + ownedVehicles;
    }
}

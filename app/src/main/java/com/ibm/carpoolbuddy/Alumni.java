package com.ibm.carpoolbuddy;

import java.util.ArrayList;

/**
 * This class details the characteristics of the alumni object (which extends the user object),
 * including the constructors, getters, setters and toString method
 */
public class Alumni extends User {
    private String graduateYear;

    public Alumni() {

    }

    public Alumni(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    public Alumni(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> bookedVehicles, int environmentPoints, String graduateYear) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles, bookedVehicles, environmentPoints);
        this.graduateYear = graduateYear;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    @Override
    public String toString() {
        return "Alumni: \n" +
                "Graduate Year = " + graduateYear +
                ", User ID = " + getUid() +
                ", Name = " + getName() +
                ", Email = " + getEmail() +
                ", User Type = " + getUserType() +
                ", Price Multiplier = " + getPriceMultiplier() +
                ", Owned Vehicles = " + getOwnedVehicles();
    }
}

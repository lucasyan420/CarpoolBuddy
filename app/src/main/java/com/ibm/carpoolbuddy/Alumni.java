package com.ibm.carpoolbuddy;

import java.util.ArrayList;

public class Alumni extends User{
    private String graduateYear;

    public Alumni()
    {

    }

    public Alumni(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    public Alumni(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String graduateYear) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
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

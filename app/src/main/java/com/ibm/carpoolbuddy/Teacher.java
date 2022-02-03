package com.ibm.carpoolbuddy;

import java.util.ArrayList;

/**
 * This class details the characteristics of the teacher object (which extends the user object),
 * including the constructors, getters, setters and toString method
 */
public class Teacher extends User {
    private String SchoolTitle;

    public Teacher() {

    }

    public Teacher(String SchoolTitle) {
        this.SchoolTitle = SchoolTitle;
    }

    public Teacher(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> bookedVehicles, int environmentPoints, String SchoolTitle) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles, bookedVehicles, environmentPoints);
        this.SchoolTitle = SchoolTitle;
    }

    public String getSchoolTitle() {
        return SchoolTitle;
    }

    public void setSchoolTitle(String SchoolTitle) {
        this.SchoolTitle = SchoolTitle;
    }

    @Override
    public String toString() {
        return "Teacher: \n" +
                "School Title = " + SchoolTitle +
                ", User ID = " + getUid() +
                ", Name = " + getName() +
                ", Email = " + getEmail() +
                ", User Type = " + getUserType() +
                ", Price Multiplier = " + getPriceMultiplier() +
                ", Owned Vehicles = " + getOwnedVehicles() +
                ", Booked Vehicles = " + getBookedVehicles();
    }
}

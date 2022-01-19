package com.ibm.carpoolbuddy;

import java.util.ArrayList;

public class Teacher extends User{
    private String SchoolTitle;

    public Teacher()
    {

    }

    public Teacher(String SchoolTitle) {
        this.SchoolTitle = SchoolTitle;
    }

    public Teacher(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String SchoolTitle) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
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
                ", Owned Vehicles = " + getOwnedVehicles();
    }
}

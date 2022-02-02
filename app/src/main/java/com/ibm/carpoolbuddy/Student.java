package com.ibm.carpoolbuddy;
import java.util.*;

public class Student extends User{
    private String graduatingYear;
    private ArrayList<String> parentUIDs;

    public Student(){

    }

    public Student(String graduatingYear, ArrayList<String> parentUIDs) {
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public Student(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> bookedVehicles, int environmentPoints, String graduatingYear, ArrayList<String> parentUIDs) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles, bookedVehicles, environmentPoints);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public String getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(String graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList<String> getParentUIDs() {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList<String> parentUIDs) {
        this.parentUIDs = parentUIDs;
    }

    @Override
    public String toString() {
        return "Student: \n" +
                "Graduating Year = " + graduatingYear +
                ", Parent User IDs = " + parentUIDs +
                ", User ID = " + getUid() +
                ", Name = " + getName() +
                ", Email = " + getEmail() +
                ", User Type = " + getUserType() +
                ", Price Multiplier = " + getPriceMultiplier() +
                ", Owned Vehicles = " + getOwnedVehicles();
    }


}

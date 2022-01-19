package com.ibm.carpoolbuddy;
import java.util.*;

public class Parent extends User{
    private ArrayList<String> childrenUIDs;

    public Parent()
    {

    }

    public Parent(ArrayList<String> childrenUIDs) {
        this.childrenUIDs = childrenUIDs;
    }

    public Parent(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> childrenUIDs) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.childrenUIDs = childrenUIDs;
    }

    public ArrayList<String> getChildrenUIDs() {
        return childrenUIDs;
    }

    public void setChildrenUIDs(ArrayList<String> childrenUIDs) {
        this.childrenUIDs = childrenUIDs;
    }

    @Override
    public String toString() {
        return "Parent: \n" +
                "Children User IDs = " + childrenUIDs +
                ", User ID = " + getUid() +
                ", Name = " + getName() +
                ", Email = " + getEmail() +
                ", User Type = " + getUserType() +
                ", Price Multiplier = " + getPriceMultiplier() +
                ", Owned Vehicles = " + getOwnedVehicles();
    }

}

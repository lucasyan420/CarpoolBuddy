package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logOut(View v)
    {
        System.out.println("Log out test");
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToVehicleInfo(View v)
    {
        Intent goToVehicleInfoIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(goToVehicleInfoIntent);
        finish();
    }

    public void goToUserProfile(View v)
    {
        Intent goToUserProfileIntent = new Intent(this, UserProfileActivity.class);
        startActivity(goToUserProfileIntent);
        finish();
    }

    public void goToAddVehicle(View v)
    {
        Intent goToAddVehicleIntent = new Intent(this, AddVehicleActivity.class);
        startActivity(goToAddVehicleIntent);
        finish();
    }

}
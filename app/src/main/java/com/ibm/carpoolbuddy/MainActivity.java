package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private String currentUserType;
    private String currentUserID;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle intentInfo = getIntent().getExtras();
        if(intentInfo != null){
            currentUserType = intentInfo.getString("UserType");
            currentUserID = intentInfo.getString("UserID");
            currentUserName = intentInfo.getString("UserName");

            System.out.println("Testing testing: " + currentUserType + currentUserID + currentUserName);
        }
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
        goToVehicleInfoIntent.putExtra("UserType", currentUserType);
        goToVehicleInfoIntent.putExtra("UserID", currentUserID);
        goToVehicleInfoIntent.putExtra("UserName", currentUserName);

        startActivity(goToVehicleInfoIntent);
        finish();
    }

    public void goToUserProfile(View v)
    {
        Intent goToUserProfileIntent = new Intent(this, UserProfileActivity.class);
        goToUserProfileIntent.putExtra("UserType", currentUserType);
        goToUserProfileIntent.putExtra("UserID", currentUserID);
        goToUserProfileIntent.putExtra("UserName", currentUserName);

        startActivity(goToUserProfileIntent);
        finish();
    }

    public void goToAddVehicle(View v)
    {
        Intent goToAddVehicleIntent = new Intent(this, AddVehicleActivity.class);
        goToAddVehicleIntent.putExtra("UserType", currentUserType);
        goToAddVehicleIntent.putExtra("UserID", currentUserID);
        goToAddVehicleIntent.putExtra("UserName", currentUserName);

        startActivity(goToAddVehicleIntent);
        finish();
    }

    public void goToLuckyReward(View v) {
        Intent goToLuckyRewardIntent = new Intent(this, LuckyRewardActivity.class);
        goToLuckyRewardIntent.putExtra("UserType", currentUserType);
        goToLuckyRewardIntent.putExtra("UserID", currentUserID);
        goToLuckyRewardIntent.putExtra("UserName", currentUserName);

        startActivity(goToLuckyRewardIntent);
        finish();
    }
}
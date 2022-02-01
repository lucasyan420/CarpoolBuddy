package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VehicleProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Vehicle vehicleInfo;

    private TextView vehicleType;
    private TextView vehicleBrand;
    private TextView vehicleModel;
    private TextView vehicleID;
    private TextView vehicleOwner;
    private TextView vehiclePrice;
    private TextView vehicleSeatsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        System.out.println("Profile Activity");

        String ids = "ID not set";
        String tests = "";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            ids = extras.getString("Description");
            tests = extras.getString("IDs");
            System.out.println(ids);
            System.out.println("Test" + tests);
        }
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(goBackIntent);
        finish();
    }
}
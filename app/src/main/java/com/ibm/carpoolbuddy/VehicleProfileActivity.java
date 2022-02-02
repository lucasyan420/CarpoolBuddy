package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VehicleProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseFirestore firestore;
    private Vehicle vehicleInfo;

    private TextView vehicleTypeTextView;
    private TextView vehicleBrandTextView;
    private TextView vehicleModelTextView;
    private TextView vehicleIDTextView;
    private TextView vehicleOwnerTextView;
    private TextView vehiclePriceTextView;
    private TextView vehicleSeatsLeftTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        System.out.println("Profile Activity");

        String[] descriptions;
        String vehicleType;
        String vehicleBrand;
        String vehicleModel;


        String id;
        String price;
        String seatsLeft;

        vehicleTypeTextView = findViewById(R.id.vehicleTypeText_vehicleProfileActivity);
        vehicleBrandTextView = findViewById(R.id.brandText_vehicleProfileActivity);
        vehicleModelTextView = findViewById(R.id.modelText_vehicleProfileActivity);
        vehicleIDTextView = findViewById(R.id.vehicleIDText_vehicleProfileActivity);
        vehicleOwnerTextView = findViewById(R.id.ownerText_vehicleProfileActivity);
        vehiclePriceTextView = findViewById(R.id.priceTextView_vehicleProfileActivity);
        vehicleSeatsLeftTextView = findViewById(R.id.seatsLeftText_vehicleProfileActivity);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            descriptions = extras.getString("Description").split(",");
            vehicleType = descriptions[0].replace("Type: ", "");
            vehicleBrand = descriptions[1].replace("Brand: ", "");
            vehicleModel = descriptions[2].replace("Model: ", "");

            id = extras.getString("ID");
            price = extras.getString("Price");
            seatsLeft = extras.getString("Seats Left");

//            if(vehicleType.equals("Car"))
//            {
//                firestore.collection("AllObjects/AllVehicles/Cars").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()){
//                            String ownerID;
//                            String ownerName;
//                            DocumentSnapshot ds = task.getResult();
//
//                            Car car = ds.toObject(Car.class);
//                            ownerID = car.getOwnerID();
////                            firestore.collection("AllObjects/AllUsers")
//                        }
//                    }
//                });
//            }

            vehicleTypeTextView.setText(vehicleType);
            vehicleBrandTextView.setText(vehicleBrand);
            vehicleModelTextView.setText(vehicleModel);
            vehicleIDTextView.setText(id);
            vehiclePriceTextView.setText("$" + price);
            vehicleSeatsLeftTextView.setText(seatsLeft);
        }
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(goBackIntent);
        finish();
    }
}
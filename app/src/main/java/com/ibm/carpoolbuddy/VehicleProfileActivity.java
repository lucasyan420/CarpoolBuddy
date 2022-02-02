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
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Vehicle vehicleInfo;

    private TextView vehicleTypeTextView;
    private TextView vehicleBrandTextView;
    private TextView vehicleModelTextView;
    private TextView vehicleIDTextView;
    private TextView vehicleOwnerTextView;
    private TextView vehiclePriceTextView;
    private TextView vehicleSeatsLeftTextView;

    private String currentUserType;
    private String currentUserID;
    private String currentUserName;

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

        Bundle intentInfo = getIntent().getExtras();
        if(intentInfo != null){
            descriptions = intentInfo.getString("Description").split(",");
            vehicleType = descriptions[0].replace("Type: ", "");
            vehicleBrand = descriptions[1].replace("Brand: ", "");
            vehicleModel = descriptions[2].replace("Model: ", "");

            id = intentInfo.getString("ID");
            price = intentInfo.getString("Price");
            seatsLeft = intentInfo.getString("Seats Left");

            currentUserType = intentInfo.getString("UserType");
            currentUserID = intentInfo.getString("UserID");
            currentUserName = intentInfo.getString("UserName");

            System.out.println("Final test " + currentUserType + currentUserID + currentUserName);

            try{
                if(vehicleType.equals("Car"))
                {
                    firestore.collection("AllObjects/AllVehicles/Cars").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                String ownerID;
                                String ownerName;
                                DocumentSnapshot ds = task.getResult();

                                Car car = ds.toObject(Car.class);
                                ownerName = car.getOwnerName();
                                vehicleOwnerTextView.setText(ownerName);
                            }
                        }
                    });
                }
                else if(vehicleType.equals("Electric Car"))
                {
                    firestore.collection("AllObjects/AllVehicles/ElectricCars").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                String ownerID;
                                String ownerName;
                                DocumentSnapshot ds = task.getResult();

                                ElectricCar electricCar = ds.toObject(ElectricCar.class);
                                ownerName = electricCar.getOwnerName();
                                vehicleOwnerTextView.setText(ownerName);
                            }
                        }
                    });
                }
                else if(vehicleType.equals("Motorcycle"))
                {
                    firestore.collection("AllObjects/AllVehicles/Motorcycle").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                String ownerID;
                                String ownerName;
                                DocumentSnapshot ds = task.getResult();

                                Motorcycle motorcycle = ds.toObject(Motorcycle.class);
                                ownerName = motorcycle.getOwnerName();
                                vehicleOwnerTextView.setText(ownerName);
                            }
                        }
                    });
                }
            }
            catch(Exception err){
                err.printStackTrace();
            }

            vehicleTypeTextView.setText(vehicleType);
            vehicleBrandTextView.setText(vehicleBrand);
            vehicleModelTextView.setText(vehicleModel);
            vehicleIDTextView.setText(id);
            vehiclePriceTextView.setText("$" + price);
            vehicleSeatsLeftTextView.setText(seatsLeft);
        }
    }

    public void bookRide(View v)
    {
        System.out.println("booking ride");
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(goBackIntent);
        finish();
    }
}
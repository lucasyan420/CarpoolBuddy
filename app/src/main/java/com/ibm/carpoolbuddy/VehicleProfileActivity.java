package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
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
    private TextView vehicleLocationTextView;

    private String currentUserType;
    private String currentUserID;
    private String currentUserName;

    private String ownerID;

    String[] descriptions;
    String vehicleType;
    String vehicleBrand;
    String vehicleModel;

    String id;
    String price;
    String seatsLeft;
    String location;
    String ownerName;

    private int capacityLeft;

    private int environmentPoints;

    /**
     * When a vehicle is selected, a page containing its information is displayed with the option
     * to book a ride
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        System.out.println("Profile Activity");

        vehicleTypeTextView = findViewById(R.id.vehicleTypeText_vehicleProfileActivity);
        vehicleBrandTextView = findViewById(R.id.brandText_vehicleProfileActivity);
        vehicleModelTextView = findViewById(R.id.modelText_vehicleProfileActivity);
        vehicleIDTextView = findViewById(R.id.vehicleIDText_vehicleProfileActivity);
        vehicleOwnerTextView = findViewById(R.id.ownerText_vehicleProfileActivity);
        vehiclePriceTextView = findViewById(R.id.priceTextView_vehicleProfileActivity);
        vehicleSeatsLeftTextView = findViewById(R.id.seatsLeftText_vehicleProfileActivity);
        vehicleLocationTextView = findViewById(R.id.locationTextView_vehicleProfileActivity);

        Bundle intentInfo = getIntent().getExtras();
        if (intentInfo != null) {
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

            location = intentInfo.getString("Location");

            System.out.println("Final test " + currentUserType + currentUserID + currentUserName);

            try {
                if (vehicleType.equals("Car")) {
                    firestore.collection("AllObjects/AllVehicles/Cars").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                String ownerName;
                                DocumentSnapshot ds = task.getResult();

                                Car car = ds.toObject(Car.class);
                                ownerName = car.getOwnerName();

                                capacityLeft = car.getCapacity();
                                vehicleOwnerTextView.setText(ownerName);

                                ownerID = car.getOwnerID();

                                environmentPoints = 5;
                            }
                        }
                    });
                } else if (vehicleType.equals("Electric Car")) {
                    firestore.collection("AllObjects/AllVehicles/ElectricCars").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot ds = task.getResult();

                                ElectricCar electricCar = ds.toObject(ElectricCar.class);
                                ownerName = electricCar.getOwnerName();
                                capacityLeft = electricCar.getCapacity();
                                vehicleOwnerTextView.setText(ownerName);

                                environmentPoints = 20;
                            }
                        }
                    });
                } else if (vehicleType.equals("Motorcycle")) {
                    firestore.collection("AllObjects/AllVehicles/Motorcycle").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot ds = task.getResult();

                                Motorcycle motorcycle = ds.toObject(Motorcycle.class);
                                ownerName = motorcycle.getOwnerName();
                                capacityLeft = motorcycle.getCapacity();
                                vehicleOwnerTextView.setText(ownerName);

                                ownerID = motorcycle.getOwnerID();

                                environmentPoints = 10;
                            }
                        }
                    });
                }
            } catch (Exception err) {
                err.printStackTrace();
            }

            vehicleTypeTextView.setText(vehicleType);
            vehicleBrandTextView.setText(vehicleBrand);
            vehicleModelTextView.setText(vehicleModel);
            vehicleIDTextView.setText(id);
            vehiclePriceTextView.setText("$" + price);
            vehicleSeatsLeftTextView.setText(seatsLeft);
            vehicleLocationTextView.setText(location);

            System.out.println("owner id test 1:" + ownerID);
        }
    }

    /**
     * If the ride still has space (more than 0 seats left), users can book a ride. If booked,
     * the vehicle's ridersUIDs is updated with the current user's id. The vehicle's capacity also
     * decreases by one. UpdateUserRides is called to update user's booked rides and
     * updateEnvironmentPoints to add to environmentPoints tally for user
     *
     * @param v
     */
    public void bookRide(View v) {
        System.out.println("booking ride");
        if (capacityLeft < 1) {
            Toast.makeText(getApplicationContext(), "No seats left", Toast.LENGTH_SHORT).show();
        } else {
            if (vehicleType.equals("Car")) {
                firestore.collection("AllObjects/AllVehicles/Cars").document(id).update("ridersUIDs", FieldValue.arrayUnion(currentUserID));
                firestore.collection("AllObjects/AllVehicles/Cars").document(id).update("capacity", FieldValue.increment(-1));
                updateUserRides();
            } else if (vehicleType.equals("Electric Car")) {
                firestore.collection("AllObjects/AllVehicles/ElectricCars").document(id).update("ridersUIDs", FieldValue.arrayUnion(currentUserID));
                firestore.collection("AllObjects/AllVehicles/ElectricCars").document(id).update("capacity", FieldValue.increment(-1));
                updateUserRides();
            } else if (vehicleType.equals("Motorcycle")) {
                firestore.collection("AllObjects/AllVehicles/Motorcycle").document(id).update("ridersUIDs", FieldValue.arrayUnion(currentUserID));
                firestore.collection("AllObjects/AllVehicles/Motorcycle").document(id).update("capacity", FieldValue.increment(-1));
                updateUserRides();
            }
            updateEnvironmentPoints();
            goBack();
        }
    }

    /**
     * When booking ride, user's bookedVehicles arraylist is updated with the booked ride's id.
     */
    public void updateUserRides() {
        try {
            System.out.println("Testing this thing" + currentUserType);
            if (currentUserType.equals("student")) {
                firestore.collection("AllObjects/AllUsers/students").document(currentUserID).update("bookedVehicles", FieldValue.arrayUnion(id));
            } else if (currentUserType.equals("teacher")) {
                firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).update("bookedVehicles", FieldValue.arrayUnion(id));
            } else if (currentUserType.equals("alumni")) {
                firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).update("bookedVehicles", FieldValue.arrayUnion(id));
            } else if (currentUserType.equals("parent")) {
                firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).update("bookedVehicles", FieldValue.arrayUnion(id));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * User's environment points is updated by different increments depending on vehicle type every
     * time a ride is booked
     */
    public void updateEnvironmentPoints() {
        if (currentUserType.equals("student")) {
            firestore.collection("AllObjects/AllUsers/students").document(currentUserID).update("environmentPoints", FieldValue.increment(environmentPoints));
        } else if (currentUserType.equals("parent")) {
            firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).update("environmentPoints", FieldValue.increment(environmentPoints));
        } else if (currentUserType.equals("alumni")) {
            firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).update("environmentPoints", FieldValue.increment(environmentPoints));
        } else if (currentUserType.equals("teacher")) {
            firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).update("environmentPoints", FieldValue.increment(environmentPoints));
        }
    }

    public void closeRide(View v) {
        if (currentUserName.equals(ownerName)) {
            if (vehicleType.equals("Car")) {
                firestore.collection("AllObjects/AllVehicles/Cars").document(id).update("capacity", 0);
            } else if (vehicleType.equals("Electric Car")) {
                firestore.collection("AllObjects/AllVehicles/ElectricCars").document(id).update("capacity", 0);
            } else if (vehicleType.equals("Motorcycle")) {
                firestore.collection("AllObjects/AllVehicles/Motorcycle").document(id).update("capacity", 0);
            }
            goBack();
        } else {
            Toast.makeText(getApplicationContext(), "You are not the owner", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Goes back to vehicleInfoActivity
     */
    public void goBack() {
        Intent goBackIntent = new Intent(this, VehiclesInfoActivity.class);
        goBackIntent.putExtra("UserType", currentUserType);
        goBackIntent.putExtra("UserID", currentUserID);
        goBackIntent.putExtra("UserName", currentUserName);
        startActivity(goBackIntent);
        finish();
    }

    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, VehiclesInfoActivity.class);
        goBackIntent.putExtra("UserType", currentUserType);
        goBackIntent.putExtra("UserID", currentUserID);
        goBackIntent.putExtra("UserName", currentUserName);
        startActivity(goBackIntent);
        finish();
    }
}
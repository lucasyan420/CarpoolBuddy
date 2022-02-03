package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;

/**
 * This class allows users to add a vehicle to the available carpools. Users enter in descriptions
 * about the offered ride and the program adds the vehicle to the firebase database. The vehicle
 * has a key-value pairing indicating the owner of the vehicle as the current logged in user, and
 * the user's profile also contains a list of vehicles he/she owns which would be updated with this
 * newest vehicle. Depending on the type of vehicle selected, the type of vehicle and UI also
 * changes to incorporate the specific characteristics of each vehicle
 */

public class AddVehicleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private TextView variable1TextView;
    private TextView variable2TextView;
    private EditText variable1EditText;
    private EditText variable2EditText;

    private EditText brandEditText;
    private EditText modelEditText;
    private EditText capacityEditText;
    private EditText locationEditText;

    private Spinner vehicleTypes;
    private String selectedType;

    private String currentUserType;
    private String currentUserID;
    private String currentUserName;
    private String userType;
    private String vehicleID;

    ArrayList<String> ridersUIDs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        Bundle intentInfo = getIntent().getExtras();
        if (intentInfo != null) {
            currentUserType = intentInfo.getString("UserType");
            currentUserID = intentInfo.getString("UserID");
            currentUserName = intentInfo.getString("UserName");

            System.out.println("Testing testing 2: " + currentUserType + currentUserID + currentUserName);
        }

        variable1TextView = findViewById(R.id.variableTextView1_addVehicleActivity);
        variable2TextView = findViewById(R.id.variableTextView2_addVehicleActivity);
        variable1EditText = findViewById(R.id.variableEditText1_addVehicleActivity);
        variable2EditText = findViewById(R.id.variableEditText2_addVehicleActivity);

        brandEditText = findViewById(R.id.brandEditText_addVehicleActivity);
        modelEditText = findViewById(R.id.modelEditText_addVehicleActivity);
        capacityEditText = findViewById(R.id.capacityEditText_addVehicleActivity);
        locationEditText = findViewById(R.id.locationEditText_addVehicleActivity);

        vehicleTypes = findViewById(R.id.typeSpinner_addVehicleActivity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicleTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypes.setAdapter(adapter);
        vehicleTypes.setOnItemSelectedListener(this);

    }

    /**
     * On onclick, the other methods to add vehicles and return to main activity is called
     * @param v is for onclicking
     */
    public void addVehicle(View v) {
//        findUser();
        addNewVehicle();
//        updateOwnedVehicles();
        goBack();
    }
//
//    private boolean formValid() {
//        return false;
//    }
//
//    public void findUserInStudent() {
//        try {
//            String currentEmail = mAuth.getCurrentUser().getEmail();
//            firestore.collection("AllObjects/AllUsers/students").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        try {
//                            currentUserID = task.getResult().getDocuments().get(0).getId();
//                            userType = "student";
//
//                            firestore.collection("AllObjects/AllUsers/students").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    DocumentSnapshot document = task.getResult();
//                                    Student currStudent = document.toObject(Student.class);
//                                    currentUserName = currStudent.getName();
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("User type is " + userType);
//                        System.out.println("Fourth test: " + currentUserID);
//                    } else {
//                        Log.d("Fail", "User not a student");
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void findUserInParent() {
//        try {
//            String currentEmail = mAuth.getCurrentUser().getEmail();
//            firestore.collection("AllObjects/AllUsers/parents").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        try {
//                            currentUserID = task.getResult().getDocuments().get(0).getId();
//                            userType = "parent";
//
//                            firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    DocumentSnapshot document = task.getResult();
//                                    Parent currParent = document.toObject(Parent.class);
//                                    currentUserName = currParent.getName();
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("User type is " + userType);
//                        System.out.println("Fourth test: " + currentUserID);
//
//                    } else {
//                        Log.d("Fail", "User not a parent");
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void findUserInAlumni() {
//        try {
//            String currentEmail = mAuth.getCurrentUser().getEmail();
//            firestore.collection("AllObjects/AllUsers/alums").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        try {
//                            currentUserID = task.getResult().getDocuments().get(0).getId();
//                            userType = "alumni";
//
//                            firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    DocumentSnapshot document = task.getResult();
//                                    Alumni currAlumni = document.toObject(Alumni.class);
//                                    currentUserName = currAlumni.getName();
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("User type is " + userType);
//                        System.out.println("Fourth test: " + currentUserID);
//                    } else {
//                        Log.d("Fail", "User not an alum");
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void findUserInTeacher() {
//        try {
//            String currentEmail = mAuth.getCurrentUser().getEmail();
//            firestore.collection("AllObjects/AllUsers/teachers").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        try {
//                            currentUserID = task.getResult().getDocuments().get(0).getId();
//                            userType = "teacher";
//
//                            firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    DocumentSnapshot document = task.getResult();
//                                    Teacher currTeacher = document.toObject(Teacher.class);
//                                    currentUserName = currTeacher.getName();
//                                    System.out.println("Testing if this works: " + currentUserName);
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("User type is " + userType);
//                        System.out.println("Fourth test: " + currentUserID);
//                    } else {
//                        Log.d("Fail", "User not a teacher");
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void getUserName() {
//        try{
//            if(userType.equals("teacher"))
//            {
//                System.out.println("Testing this student thing");
//                firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    DocumentSnapshot document = task.getResult();
//                    Teacher currTeacher = document.toObject(Teacher.class);
//                    currentUserName = currTeacher.getName();
//                    System.out.println("Testing if this works: " + currentUserName);
//                }
//            });
//            }
//        }
//        catch(Exception err)
//        {
//            err.printStackTrace();
//        }
//        if(userType.equals("student"))
//        {
//            firestore.collection("AllObjects/AllUsers/students").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    DocumentSnapshot document = task.getResult();
//                    Student currStudent = document.toObject(Student.class);
//                    currentUserName = currStudent.getName();
//                }
//            });
//        }
//        else if(userType.equals("teacher"))
//        {
//            firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    DocumentSnapshot document = task.getResult();
//                    Teacher currTeacher = document.toObject(Teacher.class);
//                    currentUserName = currTeacher.getName();
//                }
//            });
//        }
//        else if(userType.equals("alumni"))
//        {
//            firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    DocumentSnapshot document = task.getResult();
//                    Alumni currAlumni = document.toObject(Alumni.class);
//                    currentUserName = currAlumni.getName();
//                }
//            });
//        }
//        else if(userType.equals("parent"))
//        {
//            firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    DocumentSnapshot document = task.getResult();
//                    Parent currParent = document.toObject(Parent.class);
//                    currentUserName = currParent.getName();
//                }
//            });
//        }
//    }
//
//    synchronized public void findUser()
//    {
//        findUserInStudent();
//        findUserInParent();
//        findUserInAlumni();
//        findUserInTeacher();
//        getUserName();
//    }

    /**
     * When called, this method takes in various parameters inputted by users (such as brand, model
     * , location, etc) and creates a vehicle (either car, motorcycle or electric car depending on
     * user choice) if the userID is valid. Once created, the vehicle is added to the database.
     * After this, another method to update ownership of the vehicle is called.
     */
    public void addNewVehicle() {
        String brandString = brandEditText.getText().toString();
        String modelString = modelEditText.getText().toString();
        String locationString = locationEditText.getText().toString();
        int capacityInt = Integer.parseInt(capacityEditText.getText().toString());

        if (currentUserID == null) {
            Toast.makeText(this, "Please reclick", Toast.LENGTH_SHORT).show();
        } else {
            if (selectedType.equals("Car")) {
                System.out.println("Now I'm testing this" + currentUserName);
                int range = Integer.parseInt(variable1EditText.getText().toString());
                int fuelCapacity = Integer.parseInt(variable2EditText.getText().toString());

                Car car = new Car(currentUserID, currentUserName, brandString, modelString, capacityInt, UUID.randomUUID().toString(), ridersUIDs, true, "Car", 20, locationString, range, fuelCapacity);
                try {
                    vehicleID = car.getVehicleID();
                    firestore.collection("AllObjects/AllVehicles/Cars").document(car.getVehicleID()).set(car);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (selectedType.equals("Electric Car")) {
                int batteryLife = Integer.parseInt(variable1EditText.getText().toString());
                int chargingTime = Integer.parseInt(variable2EditText.getText().toString());

                ElectricCar electricCar = new ElectricCar(currentUserID, currentUserName, brandString, modelString, capacityInt, UUID.randomUUID().toString(), ridersUIDs, true, "Electric Car", 30, locationString, batteryLife, chargingTime);
                try {
                    vehicleID = electricCar.getVehicleID();
                    firestore.collection("AllObjects/AllVehicles/ElectricCars").document(electricCar.getVehicleID()).set(electricCar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (selectedType.equals("Motorcycle")) {
                int weight = Integer.parseInt(variable1EditText.getText().toString());
                int length = Integer.parseInt(variable2EditText.getText().toString());

                Motorcycle motorcycle = new Motorcycle(currentUserID, currentUserName, brandString, modelString, capacityInt, UUID.randomUUID().toString(), ridersUIDs, true, "Motorcycle", 15, locationString, weight, length);
                try {
                    vehicleID = motorcycle.getVehicleID();
                    firestore.collection("AllObjects/AllVehicles/Motorcycle").document(motorcycle.getVehicleID()).set(motorcycle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            updateOwnedVehicles();
            goBack();
        }
    }

    /**
     * Method finds the user currently logged in and updates his/her ownedVehicles arraylist to
     * add the latest vehicle created
     */
    public void updateOwnedVehicles() {
        System.out.println("Checking" + currentUserType);
        try {
            if (currentUserType.equals("student")) {
                firestore.collection("AllObjects/AllUsers/students").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            } else if (currentUserType.equals("parent")) {
                firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            } else if (currentUserType.equals("alumni")) {
                firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            } else if (currentUserType.equals("teacher")) {
                firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Goes back to main activity
     */
    public void goBack() {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        goBackIntent.putExtra("UserType", currentUserType);
        goBackIntent.putExtra("UserID", currentUserID);
        goBackIntent.putExtra("UserName", currentUserName);
        startActivity(goBackIntent);
        finish();
    }

    /**
     * Goes back to main activity
     */
    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        goBackIntent.putExtra("UserType", currentUserType);
        goBackIntent.putExtra("UserID", currentUserID);
        goBackIntent.putExtra("UserName", currentUserName);
        startActivity(goBackIntent);
        finish();
    }

    /**
     * Depending on the type of vehicle selected (done through a drop down menu from spinner),
     * the type of characteristics asked for the app changes to match each vehicle type's unique
     * characteristics
     * @param adapterView is the spinner
     * @param view
     * @param i is the position of the adapterview
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedType = adapterView.getItemAtPosition(i).toString();
        if (selectedType.equals("Car")) {
            variable1TextView.setText("Range:");
            variable2TextView.setText("Fuel:");
            variable1EditText.setHint("500");
            variable2EditText.setHint("50");
        } else if (selectedType.equals("Electric Car")) {
            variable1TextView.setText("Battery:");
            variable2TextView.setText("Charging:");
            variable1EditText.setHint("10");
            variable2EditText.setHint("1");
        } else if (selectedType.equals("Motorcycle")) {
            variable1TextView.setText("Weight:");
            variable2TextView.setText("Length:");
            variable1EditText.setHint("200");
            variable2EditText.setHint("2");
        }
    }

    /**
     * By default the vehicle type is car
     * @param adapterView is the spinner
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedType = "Car";
        variable1TextView.setText("Range:");
        variable2TextView.setText("Fuel:");
        variable1EditText.setHint("500");
        variable2EditText.setHint("50");
    }
}
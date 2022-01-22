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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;

public class AddVehicleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private TextView variable1TextView;
    private TextView variable2TextView;
    private TextView variable3TextView;
    private EditText variable1EditText;
    private EditText variable2EditText;
    private EditText variable3EditText;

    private EditText brandEditText;
    private EditText modelEditText;
    private EditText capacityEditText;

    private Spinner vehicleTypes;
    private String selectedType;

    private String currentUserID;
    private String userType;
    private String vehicleID;

    ArrayList<String> ridersUIDs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        variable1TextView = findViewById(R.id.variableTextView1_addVehicleActivity);
        variable2TextView = findViewById(R.id.variableTextView2_addVehicleActivity);
        variable3TextView = findViewById(R.id.variableTextView3_addVehicleActivity);
        variable1EditText = findViewById(R.id.variableEditText1_addVehicleActivity);
        variable2EditText = findViewById(R.id.variableEditText2_addVehicleActivity);
        variable3EditText = findViewById(R.id.variableEditText3_addVehicleActivity);

        brandEditText = findViewById(R.id.brandEditText_addVehicleActivity);
        modelEditText = findViewById(R.id.modelEditText_addVehicleActivity);
        capacityEditText = findViewById(R.id.capacityEditText_addVehicleActivity);
        
        vehicleTypes = findViewById(R.id.typeSpinner_addVehicleActivity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicleTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypes.setAdapter(adapter);
        vehicleTypes.setOnItemSelectedListener(this);

    }

    public void addVehicle(View v)
    {
        findUser();
        addNewVehicle();
        updateOwnedVehicles();
    }

    private boolean formValid(){
        return false;
    }

    public void findUserInStudent()
    {
        try{
            String currentEmail = mAuth.getCurrentUser().getEmail();
            firestore.collection("AllObjects/AllUsers/students").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        try{
                            currentUserID = task.getResult().getDocuments().get(0).getId();
                            userType = "student";
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + userType);
                        System.out.println("Fourth test: " + currentUserID);
                    }
                    else{
                        Log.d("Fail", "User not a student");
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void findUserInParent()
    {
        try{
            String currentEmail = mAuth.getCurrentUser().getEmail();
            firestore.collection("AllObjects/AllUsers/parents").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        try{
                            currentUserID = task.getResult().getDocuments().get(0).getId();
                            userType = "parent";
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + userType);
                        System.out.println("Fourth test: " + currentUserID);
                    }
                    else{
                        Log.d("Fail", "User not a parent");
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void findUserInAlumni()
    {
        try{
            String currentEmail = mAuth.getCurrentUser().getEmail();
            firestore.collection("AllObjects/AllUsers/alums").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        try{
                            currentUserID = task.getResult().getDocuments().get(0).getId();
                            userType = "alumni";
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + userType);
                        System.out.println("Fourth test: " + currentUserID);
                    }
                    else{
                        Log.d("Fail", "User not an alum");
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void findUserInTeacher()
    {
        try{
            String currentEmail = mAuth.getCurrentUser().getEmail();
            firestore.collection("AllObjects/AllUsers/teachers").whereEqualTo("email", currentEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        try{
                            currentUserID = task.getResult().getDocuments().get(0).getId();
                            userType = "teacher";
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + userType);
                        System.out.println("Fourth test: " + currentUserID);
                    }
                    else{
                        Log.d("Fail", "User not a teacher");
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    synchronized public void findUser()
    {
        findUserInStudent();
        findUserInParent();
        findUserInAlumni();
        findUserInTeacher();
    }

    public void addNewVehicle(){
        String brandString = brandEditText.getText().toString();
        String modelString = modelEditText.getText().toString();
        int capacityInt = Integer.parseInt(capacityEditText.getText().toString());

        if(currentUserID == null)
        {
            Toast.makeText(this, "Please reclick", Toast.LENGTH_SHORT).show();
        }
        else{
            if(selectedType.equals("Car")){
                int range = Integer.parseInt(variable1EditText.getText().toString());
                int fuelCapacity = Integer.parseInt(variable2EditText.getText().toString());
                String safetyRating = variable3EditText.getText().toString();

                Car car = new Car(currentUserID, brandString, modelString, capacityInt, UUID.randomUUID().toString(), ridersUIDs, true, "Car", 20, range, fuelCapacity, safetyRating);
                try {
                    vehicleID = car.getVehicleID();
                    firestore.collection("AllObjects/AllVehicles/Cars").document(car.getVehicleID()).set(car);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(selectedType.equals("Electric Car")){
                int batteryLife = Integer.parseInt(variable1EditText.getText().toString());
                int chargingTime = Integer.parseInt(variable2EditText.getText().toString());
                String smartDriveFeatures = variable3EditText.getText().toString();

                ElectricCar electricCar = new ElectricCar(currentUserID, brandString, modelString, capacityInt, UUID.randomUUID().toString(), ridersUIDs, true, "Electric Car", 30, batteryLife, chargingTime, smartDriveFeatures);
                try {
                    vehicleID = electricCar.getVehicleID();
                    firestore.collection("AllObjects/AllVehicles/ElectricCars").document(electricCar.getVehicleID()).set(electricCar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(selectedType.equals("Motorcycle")){
                int weight = Integer.parseInt(variable1EditText.getText().toString());
                int length = Integer.parseInt(variable2EditText.getText().toString());
                String seatType = variable3EditText.getText().toString();

                Motorcycle motorcycle = new Motorcycle(currentUserID, brandString, modelString, capacityInt, UUID.randomUUID().toString(), ridersUIDs, true, "Motorcycle", 15, weight, length, seatType);
                try {
                    vehicleID = motorcycle.getVehicleID();
                    firestore.collection("AllObjects/AllVehicles/Motorcycle").document(motorcycle.getVehicleID()).set(motorcycle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            goBack();
        }
    }

    public void updateOwnedVehicles()
    {
        System.out.println("Checking" + userType);
        try{
            if(userType.equals("student")){
                firestore.collection("AllObjects/AllUsers/students").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            }
            else if(userType.equals("parent")){
                firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            }
            else if(userType.equals("alumni")){
                firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            }
            else if(userType.equals("teacher"))
            {
                firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).update("ownedVehicles", FieldValue.arrayUnion(vehicleID));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void goBack(){
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
        finish();
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedType = adapterView.getItemAtPosition(i).toString();
        if(selectedType.equals("Car")){
            variable1TextView.setText("Range:");
            variable2TextView.setText("Fuel:");
            variable3TextView.setText("Safety:");
            variable1EditText.setHint("500");
            variable2EditText.setHint("50");
            variable3EditText.setHint("A");
        } else if(selectedType.equals("Electric Car")){
            variable1TextView.setText("Battery:");
            variable2TextView.setText("Charging:");
            variable3TextView.setText("Features:");
            variable1EditText.setHint("10");
            variable2EditText.setHint("1");
            variable3EditText.setHint("Self Driving");
        } else if(selectedType.equals("Motorcycle")){
            variable1TextView.setText("Weight:");
            variable2TextView.setText("Length:");
            variable3TextView.setText("Seat Type:");
            variable1EditText.setHint("200");
            variable2EditText.setHint("2");
            variable3EditText.setHint("High and narrow");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedType = "Car";
        variable1TextView.setText("Range:");
        variable2TextView.setText("Fuel:");
        variable3TextView.setText("Safety:");
        variable1EditText.setHint("500");
        variable2EditText.setHint("50");
        variable3EditText.setHint("A");
    }
}
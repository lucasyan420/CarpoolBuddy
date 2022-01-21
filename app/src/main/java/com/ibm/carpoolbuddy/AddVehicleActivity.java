package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;

public class AddVehicleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseFirestore firestoreRef = FirebaseFirestore.getInstance();
    private FirebaseUser mUser;
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

    private boolean formValid(){
        return false;
    }

    private void addNewVehicle(){
//        if(selectedType.equals("Car")){
//            Car car = new Car()
//        }
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
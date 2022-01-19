package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddVehicleActivity extends AppCompatActivity {
    private FirebaseFirestore firestoreRef = FirebaseFirestore.getInstance();
    private FirebaseUser mUser;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
    }

    private boolean formValid(){
        return false;
    }

    private void addNewVehicle(){

    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
        finish();
    }

}
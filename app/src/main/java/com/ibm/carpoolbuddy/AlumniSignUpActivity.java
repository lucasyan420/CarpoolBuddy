package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is for users to create an alumni account for later log in
 */
public class AlumniSignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText nameEditText;
    private EditText graduationYearEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private ArrayList<String> ownedVehicles = new ArrayList<String>();
    private ArrayList<String> bookedVehicles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.nameEditText_alumniSignUpActivity);
        graduationYearEditText = findViewById(R.id.graduationYearEditText_alumniSignUpActivity);
        emailEditText = findViewById(R.id.emailEditText_alumniSignUpActivity);
        passwordEditText = findViewById(R.id.passwordEditText_alumniSignUpActivity);
    }

    /**
     * Users enter parameters for their alumni characteristics, and using FirebaseAuth, the program
     * creates an alumni object with the inputted characteristics (including email and password)
     * before adding the object to the database of all users
     * @param v
     */
    public void signUp(View v) {
        System.out.println("Sign up test");
        String nameString = nameEditText.getText().toString();
        String graduationYear = graduationYearEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("Test", "Successfully signed up the user");

                    Alumni alumni = new Alumni(UUID.randomUUID().toString(), nameString, emailString, "Alumni", 1, ownedVehicles, bookedVehicles, 0, graduationYear);
                    try {
//                        firestore.collection("AllUsers/students").document(student.getUid()).set(student);
                        firestore.collection("AllObjects/AllUsers/alums").document(alumni.getUid()).set(alumni);
                        System.out.println("test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("Test", "Test3");
                    goBack();
                } else {
                    Log.w("Test", "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    /**
     * Goes back to SignUpProfile Activity
     */
    public void goBack() {
        Intent goBackIntent = new Intent(this, SignUpProfile.class);
        startActivity(goBackIntent);
    }

    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, SignUpProfile.class);
        startActivity(goBackIntent);
    }
}
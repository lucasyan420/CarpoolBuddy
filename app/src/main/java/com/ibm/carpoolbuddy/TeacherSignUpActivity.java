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

public class TeacherSignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText nameEditText;
    private EditText schoolTitleEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private ArrayList<String> ownedVehicles = new ArrayList<String>();
    private ArrayList<String> bookedVehicles = new ArrayList<String>();

    /**
     * User can create a teacher account/object with characteristics
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.nameEditText_teacherSgnUpActivity);
        schoolTitleEditText = findViewById(R.id.schoolTitleEditEdit_teacherSignUpActivity);
        emailEditText = findViewById(R.id.emailEditText_teacherSignUpActivity);
        passwordEditText = findViewById(R.id.passwordEditText_teacherSignUpActivity);
    }

    /**
     * Teacher inputs various characteristics, and using FirebaseAuth, a teacher account/object
     * is created and firebase database is updated with new teacher object.
     * @param v
     */
    public void signUp(View v) {
        System.out.println("Sign up test");
        String nameString = nameEditText.getText().toString();
        String schoolTitleString = schoolTitleEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("Test", "Successfully signed up the user");

                    Teacher teacher = new Teacher(UUID.randomUUID().toString(), nameString, emailString, "Teacher", 1, ownedVehicles, bookedVehicles, 0, schoolTitleString);
                    try {
//                        firestore.collection("AllUsers/students").document(student.getUid()).set(student);
                        firestore.collection("AllObjects/AllUsers/teachers").document(teacher.getUid()).set(teacher);
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
     * Goes back to signupprofile activity
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
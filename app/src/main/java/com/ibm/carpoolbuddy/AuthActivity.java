package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private String selected;
    private Spinner sUserType;

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailEditText = findViewById(R.id.emailEditText_authActivity);
        passwordEditText = findViewById(R.id.passwordEditText_authActivity);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
    }

    public void logIn(View v)
    {
        System.out.println("Log in test");
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("LOG IN", "Successfully logged in the user");

                    FirebaseUser user = mAuth.getCurrentUser();

                    updateUI(user);
                }
                else {
                    Log.w("LOG IN", "signInWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }
        });




    }

    public void signUp(View v)
    {
        System.out.println("Sign up test");
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("SIGN UP", "Successfully signed up the user");

                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    Log.w("SIGN UP", "createUserWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }
        });
    }

    public void logOut(View v)
    {
        System.out.println("Log out test");
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    public void updateUI(FirebaseUser currentUser)
    {
        if(currentUser != null)
        {
            Intent updateUIIntent = new Intent(this, MainActivity.class);
            startActivity(updateUIIntent);
        }
        else
        {
            Intent refreshUIIntent = new Intent(this, AuthActivity.class);
            startActivity(refreshUIIntent);
        }
    }

    public void test(View v)
    {
        Alumni testAlumni1 = new Alumni(UUID.randomUUID().toString(), "Lucas", "lucasyan2005@gmail.com", "Alumni", 10, null, "2015");
        Student testStudent1 = new Student(UUID.randomUUID().toString(), "Bob", "bob@gmail.com", "Student", 1, null, "2023", null);
        Student testStudent2 = new Student(UUID.randomUUID().toString(), "jim", "jim@gmail.com", "Student", 1, null, "2023", null);

        firestore.collection("items").document("test").set(testStudent1);
        firestore.collection("AllSchools/cis/students").document(testStudent1.getUid()).set(testStudent1);
        firestore.collection("AllSchools/cis/alums").document(testAlumni1.getUid()).set(testAlumni1);
        firestore.collection("AllSchools/hkis/students").document(testStudent2.getUid()).set(testStudent2);
        firestore.collection("AllSchools/cis/students").document(testStudent2.getUid()).set(testStudent2);

//        firestore.collection("Test").document("Alumni test").set(testAlumni1);
//        firestore.collection("Test").document("Student test").set(testStudent1);
//        firestore.collection("AllSchools").document("CIS").collection("student").document(testStudent1.getUid()).set(testStudent1);
//        firestore.collection("AllSchools").document("CIS").collection("alumni").document(testAlumni1.getUid()).set(testAlumni1);
//        firestore.collection("AllSchools").document("HKIS").collection("alumni").document(testAlumni1.getUid()).set(testAlumni1);
//        firestore.collection("AllSchools/CIS/student").document(testStudent1.getUid()).set(testStudent1);
//        firestore.collection("AllSchools/CIS/alumni").document(testAlumni1.getUid()).set(testAlumni1);
//        firestore.collection("AllSchools/HKIS/alumni").document(testAlumni1.getUid()).set(testAlumni1);
//
        Log.d("Test", "ID to look for: " + testStudent1.getUid());
//
        firestore.collection("AllSchools/cis/students").document(testStudent1.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot ds = task.getResult();
//
                    User myUser = ds.toObject(User.class);
                    Student myStudent = ds.toObject(Student.class);
//
                    Log.d("Test", myUser.getName());
                    Log.d("Test", myStudent.getGraduatingYear());
                }
                else
                {

                }
            }
        });
    }
}
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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailEditText;
    private EditText passwordEditText;

    private String id;

    private String currentUserID;
    private String currentUserName;
    private String currentUserType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailEditText = findViewById(R.id.emailEditText_authActivity);
        passwordEditText = findViewById(R.id.passwordEditText_authActivity);
    }

    public void logIn(View v)
    {
        System.out.println("Log in test");
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));
//        findCurrentID();

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("LOG IN", "Successfully logged in the user");

//                    FirebaseUser user = mAuth.getCurrentUser();

                    System.out.println("Test: " + id);
                    findUser();
//                    updateUI(user);
                }
                else {
                    Log.w("LOG IN", "signInWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }
        });
    }

//    public void findCurrentID(){
//        String emailString = emailEditText.getText().toString();
//        firestore.collection("AllObjects/AllUsers/students").whereEqualTo("email", emailString).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                id = task.getResult().getDocuments().get(0).getId();
//                System.out.println("Third test: " + id);
//            }
//        });
        // firestore.collection("AllObjects/AllUsers/students").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        // @Override
        // public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        // if(task.isSuccessful() && task.getResult() != null){
        //    String userId = task.getResult().getString("uid");
        //    System.out.println("Second test: " + userId);
        // }
        // else{
        //    Log.d("Fail", "Fail");
        // }
        // }
        // });
//    }

    synchronized public void findUser(){
        findUserInStudent();
        findUserInParent();
        findUserInAlumni();
        findUserInTeacher();
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
                            currentUserType = "student";

                            firestore.collection("AllObjects/AllUsers/students").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    Student currStudent = document.toObject(Student.class);
                                    currentUserName = currStudent.getName();

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                }
                            });
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + currentUserType);
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
                            currentUserType = "parent";

                            firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    Parent currParent = document.toObject(Parent.class);
                                    currentUserName = currParent.getName();

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                }
                            });
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + currentUserType);
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
                            currentUserType = "alumni";

                            firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    Alumni currAlumni = document.toObject(Alumni.class);
                                    currentUserName = currAlumni.getName();

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                }
                            });
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + currentUserType);
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
                            currentUserType = "teacher";

                            firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    Teacher currTeacher = document.toObject(Teacher.class);
                                    currentUserName = currTeacher.getName();
                                    System.out.println("Testing if this works: " + currentUserName);

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                }
                            });
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("User type is " + currentUserType);
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

    public void signUp(View v)
    {
        Intent goToSignUpProfileIntent = new Intent(this, SignUpProfile.class);
        startActivity(goToSignUpProfileIntent);
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
            updateUIIntent.putExtra("UserType", currentUserType);
            updateUIIntent.putExtra("UserID", currentUserID);
            updateUIIntent.putExtra("UserName", currentUserName);
            System.out.println("tee" + currentUserName);
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
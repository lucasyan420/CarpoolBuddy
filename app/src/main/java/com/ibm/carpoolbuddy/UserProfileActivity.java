package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private TextView nameTextView;
    private TextView typeTextView;
    private TextView emailTextView;
    private TextView userIDTextView;
    private TextView ownedVehiclesTextView;
    private TextView bookedRidesTextView;

    private String nameString;
    private String typeString;
    private String emailString;
    private String userIDString;
    private ArrayList<String> ownedVehicles;
    private ArrayList<String> bookedRides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        nameTextView = findViewById(R.id.nameTextView_userProfileActivity);
        typeTextView = findViewById(R.id.typeTextView_userProfileActivity);
        emailTextView = findViewById(R.id.emailTextView_userProfileActivity);
        userIDTextView = findViewById(R.id.userIDTextView_userProfileActivity);
        ownedVehiclesTextView = findViewById(R.id.ownedVehiclesTextView_userProfileActivity);
        bookedRidesTextView = findViewById(R.id.bookedRidesTextView_userProfileActivity);

        Bundle intentInfo = getIntent().getExtras();
        if(intentInfo != null)
        {
            nameString = intentInfo.getString("UserName");
            typeString = intentInfo.getString("UserType");
            userIDString = intentInfo.getString("UserID");
        }

        updateUserInfo();

        nameTextView.setText(nameString);
        typeTextView.setText(typeString);
        userIDTextView.setText(userIDString);
//        ownedVehiclesTextView.setText(ownedVehicles.toString());
//        bookedRidesTextView.setText(bookedRides.toString());
    }

    public void updateUserInfo()
    {
        System.out.println(typeString);
        try{
            if(typeString.equals("student"))
            {
                firestore.collection("AllObjects/AllUsers/students").document(userIDString).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot ds = task.getResult();
                            Student currStudent = ds.toObject(Student.class);

                            emailString = currStudent.getEmail();
                            ownedVehicles = currStudent.getOwnedVehicles();
                            bookedRides = currStudent.getBookedVehicles();

                            emailTextView.setText(emailString);
                            for(String vehicles: ownedVehicles)
                            {
                                ownedVehiclesTextView.append(vehicles);
                            }
                            for(String rides: bookedRides)
                            {
                                bookedRidesTextView.append(rides);
                            }
                        }
                    }
                });
            }
            else if(typeString.equals("parent"))
            {
                firestore.collection("AllObjects/AllUsers/parents").document(userIDString).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot ds = task.getResult();
                            Student currStudent = ds.toObject(Student.class);

                            emailString = currStudent.getEmail();
                            ownedVehicles = currStudent.getOwnedVehicles();
                            bookedRides = currStudent.getBookedVehicles();

                            emailTextView.setText(emailString);
                            for(String vehicles: ownedVehicles)
                            {
                                ownedVehiclesTextView.append(vehicles);
                            }
                            for(String rides: bookedRides)
                            {
                                bookedRidesTextView.append(rides);
                            }
                        }
                    }
                });
            }
            else if(typeString.equals("alumni"))
            {
                firestore.collection("AllObjects/AllUsers/alums").document(userIDString).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot ds = task.getResult();
                            Student currStudent = ds.toObject(Student.class);

                            emailString = currStudent.getEmail();
                            ownedVehicles = currStudent.getOwnedVehicles();
                            bookedRides = currStudent.getBookedVehicles();

                            emailTextView.setText(emailString);
                            for(String vehicles: ownedVehicles)
                            {
                                ownedVehiclesTextView.append(vehicles);
                            }
                            for(String rides: bookedRides)
                            {
                                bookedRidesTextView.append(rides);
                            }
                        }
                    }
                });
            }
            else if(typeString.equals("teacher"))
            {
                firestore.collection("AllObjects/AllUsers/teachers").document(userIDString).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot ds = task.getResult();
                            Student currStudent = ds.toObject(Student.class);

                            emailString = currStudent.getEmail();
                            ownedVehicles = currStudent.getOwnedVehicles();
                            bookedRides = currStudent.getBookedVehicles();

                            emailTextView.setText(emailString);
                            for(String vehicles: ownedVehicles)
                            {
                                ownedVehiclesTextView.append(vehicles);
                            }
                            for(String rides: bookedRides)
                            {
                                bookedRidesTextView.append(rides);
                            }
                        }
                    }
                });
            }
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        goBackIntent.putExtra("UserType", typeString);
        goBackIntent.putExtra("UserName", nameString);
        goBackIntent.putExtra("UserID", userIDString);
        startActivity(goBackIntent);
        finish();
    }
}
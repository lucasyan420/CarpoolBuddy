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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class ParentSignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText childNameEditText;

    private String id;

    private ArrayList<String> childrenIDs = new ArrayList<String>();
    private ArrayList<String> ownedVehicles = new ArrayList<String>();
    private ArrayList<String> bookedVehicles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.nameEditText_parentSignUpActivity);
        emailEditText = findViewById(R.id.emailEditText_parentSignUpActivity);
        passwordEditText = findViewById(R.id.passwordEditText_parentSignUpActivity);
        childNameEditText = findViewById(R.id.childNameEditText_parentSignUpActivity);
    }

    public void signUp(View v){
        System.out.println("Sign up test");
        String nameString = nameEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Test", "Successfully signed up the user");
                    Parent parent = new Parent(UUID.randomUUID().toString(), nameString, emailString, "Parent", 1, ownedVehicles, bookedVehicles, childrenIDs);
                    try {
                        firestore.collection("AllObjects/AllUsers/parents").document(parent.getUid()).set(parent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    updateParentRelationships();
                    goBack();
                }
                else {
                    Log.w("Test", "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    public void updateParentRelationships(){
        updateStudentID();

        String nameString = nameEditText.getText().toString();

        firestore.collection("AllObjects/AllUsers/parents").whereEqualTo("name", nameString).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                id = task.getResult().getDocuments().get(0).getId();

                updateRelationships(id);
            }
        });
    }

    public void updateStudentID(){
        String childNameString = childNameEditText.getText().toString();

        firestore.collection("AllObjects/AllUsers/students").whereEqualTo("name", childNameString).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Test", document.getId() + " => " + document.getData());
                        childrenIDs.add(document.getId());
                    }
                }
                else {
                    Log.d("Test", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void updateRelationships(String id)
    {
        //Update this student's parentUIDs with the parentIDs array
        firestore.collection("AllObjects/AllUsers/parents/").document(id).update("childrenUIDs", childrenIDs);
        //For each parent in parentIDs array, will update childrenUIDs of the parent with this student's id
        for(String child: childrenIDs)
        {
            firestore.collection("AllObjects/AllUsers/students/").document(child).update("parentUIDs", FieldValue.arrayUnion(id));
        }
    }

    public void goBack(){
        Intent goBackIntent = new Intent(this, SignUpProfile.class);
        startActivity(goBackIntent);
    }

    public void goBack(View v){
        Intent goBackIntent = new Intent(this, SignUpProfile.class);
        startActivity(goBackIntent);
    }
}
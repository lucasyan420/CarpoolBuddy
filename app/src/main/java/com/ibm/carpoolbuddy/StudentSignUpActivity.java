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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;

public class StudentSignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText nameEditText;
    private EditText graduationYearEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText parentNameEditText;

    private String id;

    private ArrayList<String> parentIDs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.nameEditText_studentSignUpActivity);
        graduationYearEditText = findViewById(R.id.graduationYearEditText_studentSignUpActivity);
        emailEditText = findViewById(R.id.emailEditText_studentSignUpActivity);
        passwordEditText = findViewById(R.id.passwordEditText_studentSignUpActivity);
        parentNameEditText = findViewById(R.id.parentNameEditText_studentSignUpActivity);
    }

    public void signUp(View v){
        System.out.println("Sign up test");
        String nameString = nameEditText.getText().toString();
        String graduationYear = graduationYearEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();
        System.out.println(String.format("email: %s and password: %s", emailString, passwordString));

        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Test", "Successfully signed up the user");
                    Student student = new Student(UUID.randomUUID().toString(), nameString, emailString, "Student", 1, null, graduationYear, parentIDs);
                    try {
                        firestore.collection("AllObjects/AllUsers/students").document(student.getUid()).set(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    updateStudentRelationships();
                    goBack();
                }
                else {
                    Log.w("Test", "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    public void updateStudentRelationships(){
        updateParentID();

        String nameString = nameEditText.getText().toString();

        firestore.collection("AllObjects/AllUsers/students").whereEqualTo("name", nameString).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                id = task.getResult().getDocuments().get(0).getId();

                updateRelationships(id);
            }
        });
    }


    public void updateParentID(){
        String parentNameString = parentNameEditText.getText().toString();

        firestore.collection("AllObjects/AllUsers/parents").whereEqualTo("name", parentNameString).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Test", document.getId() + " => " + document.getData());
                        parentIDs.add(document.getId());
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
        firestore.collection("AllObjects/AllUsers/students/").document(id).update("parentUIDs", parentIDs);
        //For each parent in parentIDs array, will update childrenUIDs of the parent with this student's id
        for(String parents: parentIDs)
        {
            firestore.collection("AllObjects/AllUsers/parents/").document(parents).update("childrenUIDs", FieldValue.arrayUnion(id));
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
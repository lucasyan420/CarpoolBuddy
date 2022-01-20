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

import java.util.UUID;

public class ParentSignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.nameEditText_parentSignUpActivity);
        emailEditText = findViewById(R.id.emailEditText_parentSignUpActivity);
        passwordEditText = findViewById(R.id.passwordEditText_parentSignUpActivity);
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

                    Parent parent = new Parent(UUID.randomUUID().toString(), nameString, emailString, "Parent", 1, null, null);
                    try {
                        firestore.collection("AllObjects/AllUsers/parents").document(parent.getUid()).set(parent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("Test", "Test3");
                    goBack();
                }
                else {
                    Log.w("Test", "createUserWithEmail:failure", task.getException());
                }
            }
        });
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
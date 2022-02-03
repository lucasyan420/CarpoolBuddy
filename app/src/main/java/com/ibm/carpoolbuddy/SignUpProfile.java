package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Page where users can click buttons to go to student, alumni, teacher or parent signup, or go back
 */
public class SignUpProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);
    }

    public void goToStudentSignUp(View v) {
        Intent goToStudentSignUpIntent = new Intent(this, StudentSignUpActivity.class);
        startActivity(goToStudentSignUpIntent);
        finish();
    }

    public void goToAlumniSignUp(View v) {
        Intent goToAlumniSignUpIntent = new Intent(this, AlumniSignUpActivity.class);
        startActivity(goToAlumniSignUpIntent);
        finish();
    }

    public void goToTeacherSignUp(View v) {
        Intent goToTeacherSignUpIntent = new Intent(this, TeacherSignUpActivity.class);
        startActivity(goToTeacherSignUpIntent);
        finish();
    }

    public void goToParentSignUp(View v) {
        Intent goToParentSignUpIntent = new Intent(this, ParentSignUpActivity.class);
        startActivity(goToParentSignUpIntent);
        finish();
    }


    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, AuthActivity.class);
        startActivity(goBackIntent);
        finish();
    }
}

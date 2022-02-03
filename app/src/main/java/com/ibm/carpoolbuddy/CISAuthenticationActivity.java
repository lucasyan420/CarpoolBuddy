package com.ibm.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * When users want to create a new account, this page opens which asks for an authentication
 * passcode given to CIS community members. This way, only CIS community members can create
 * accounts, and thus log in. If passcode is correct, the page for creating accounts opens up,
 * otherwise, a message pops up stating wrong passcode
 */
public class CISAuthenticationActivity extends AppCompatActivity {
    private EditText passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cisauthentication);

        passcode = findViewById(R.id.CISPasscodeEditText_CISAuthenticationActivity);
    }

    /**
     * When the correct passcode (CIS) is entered, the new page is loaded. Otherwise, the
     * toast message is displayed.
     * @param v
     */
    public void signUp(View v) {
        String enteredPasscode = passcode.getText().toString();
        System.out.println("SEEE" + enteredPasscode);
        if (enteredPasscode.equals("CIS")) {
            Intent goToSignUpProfileIntent = new Intent(this, SignUpProfile.class);
            startActivity(goToSignUpProfileIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong CIS Passcode", Toast.LENGTH_SHORT).show();
        }
    }
}
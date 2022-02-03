package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

/**
 * If users have over 50 environment points, they can flip a coin to have the chance to win
 * a free ride. If they flip heads, they win. Otherwise, they don't. Every try, they lose
 * 50 points. This incentivises users to keep on using the app and carpooling more environmentally
 * friendly options (which earn more environment points) with the chance for free carpool rides.
 * Also adds a game element so that they don't just automatically win prizes from carpooling
 */
public class LuckyRewardActivity extends AppCompatActivity {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private String currentUserType;
    private String currentUserID;
    private String currentUserName;
    private int currentEnvironmentPoints;

    private TextView instructions;
    private TextView coinFlipResults;
    private Button button;

    private final Random random = new Random();
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_reward);

        Bundle intentInfo = getIntent().getExtras();
        if (intentInfo != null) {
            currentUserType = intentInfo.getString("UserType");
            currentUserID = intentInfo.getString("UserID");
            currentUserName = intentInfo.getString("UserName");
        }

        instructions = findViewById(R.id.instructionsTextView_luckyRewardActivity);
        instructions.setText("Rules: \n" +
                "1. You must have at least 50 environmental points to play \n"
                + "2. Flip a coin: 50/50 chance \n"
                + "3. Land heads to win a free carpool ride");
        coinFlipResults = findViewById(R.id.coinFlipResult_luckyRewardActivity);
        button = findViewById(R.id.flipCoin_luckyRewardActivity);
        button.setClickable(true);

        if (currentUserType.equals("student")) {
            firestore.collection("AllObjects/AllUsers/students").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot ds = task.getResult();
                        Student student = ds.toObject(Student.class);
                        currentEnvironmentPoints = student.getEnvironmentPoints();
                    }
                }
            });
        } else if (currentUserType.equals("teacher")) {
            firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot ds = task.getResult();
                        Student student = ds.toObject(Student.class);
                        currentEnvironmentPoints = student.getEnvironmentPoints();
                    }
                }
            });
        } else if (currentUserType.equals("alumni")) {
            firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot ds = task.getResult();
                        Student student = ds.toObject(Student.class);
                        currentEnvironmentPoints = student.getEnvironmentPoints();
                    }
                }
            });
        } else if (currentUserType.equals("parent")) {
            firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot ds = task.getResult();
                        Student student = ds.toObject(Student.class);
                        currentEnvironmentPoints = student.getEnvironmentPoints();
                    }
                }
            });
        }
    }

    /**
     * Checks to see if the user has over 50 environment poitns. If yes, the coin is flipped using
     * the random java code, and if the result is 1, the winning text is displayed and the user
     * wins a free ride. If coin result is 2, user doesn't win anything. After flipping, the user
     * can't flip again until they exit. After every try, 50 points is deducted from the user's
     * environment point. If user doesn't have over 50 points, they can't play and text is displayed
     * @param v
     */
    public void flipCoin(View v) {
        if (currentEnvironmentPoints >= 50) {
            if (random.nextBoolean()) {
                result = 1;
                coinFlipResults.setText("HEADS! You win a free ride! ");
            } else {
                result = 2;
                coinFlipResults.setText("Tails :( Unlucky, keep using this app for more free ride opportunities");
            }
            button.setClickable(false);

            if (currentUserType.equals("student")) {
                firestore.collection("AllObjects/AllUsers/students").document(currentUserID).update("environmentPoints", FieldValue.increment(-50));
            } else if (currentUserType.equals("parent")) {
                firestore.collection("AllObjects/AllUsers/parents").document(currentUserID).update("environmentPoints", FieldValue.increment(-50));
            } else if (currentUserType.equals("alumni")) {
                firestore.collection("AllObjects/AllUsers/alums").document(currentUserID).update("environmentPoints", FieldValue.increment(-50));
            } else if (currentUserType.equals("teacher")) {
                firestore.collection("AllObjects/AllUsers/teachers").document(currentUserID).update("environmentPoints", FieldValue.increment(-50));
            }
        } else if (currentEnvironmentPoints < 50) {
            Toast toast = Toast.makeText(getApplicationContext(), "You don't have enough environment points. Book more rides to play", Toast.LENGTH_SHORT);
            toast.show();
            goBack();
        }
    }

    /**
     * Page goes back to main activity
     */
    public void goBack() {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        goBackIntent.putExtra("UserType", currentUserType);
        goBackIntent.putExtra("UserID", currentUserID);
        goBackIntent.putExtra("UserName", currentUserName);
        startActivity(goBackIntent);
        finish();
    }

    public void goBack(View v) {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        goBackIntent.putExtra("UserType", currentUserType);
        goBackIntent.putExtra("UserID", currentUserID);
        goBackIntent.putExtra("UserName", currentUserName);
        startActivity(goBackIntent);
        finish();
    }
}
package com.ibm.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.*;

public class VehiclesInfoActivity extends AppCompatActivity {
    RecyclerView recView;
    vehiclesAdapter myAdapter;
    private vehiclesAdapter.RecyclerViewClickListener listener;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private FirebaseUser user;
    private Vehicle vehicleInfo;
    private ArrayList<Vehicle> vehiclesList;

    private int count = 0;

    ArrayList<String> vehicleIDs = new ArrayList<String>();

    ArrayList<String> vehicleLocations = new ArrayList<String>();

    ArrayList<String> vehicleDescriptions;
    ArrayList<String> vehicleOwners = new ArrayList<String>();
    ArrayList<String> vehicleTypes = new ArrayList<String>();
    ArrayList<String> vehicleBrands = new ArrayList<String>();
    ArrayList<String> vehicleModels = new ArrayList<String>();

    ArrayList<String> vehiclePrices = new ArrayList<String>();

    ArrayList<String> vehicleSeatsLeft = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);


        vehicleDescriptions = new ArrayList();

        try{
            setOnClickListener();
            recView = findViewById(R.id.recyclerView_vehiclesInfoActivity);
            myAdapter = new vehiclesAdapter(vehicleLocations, vehicleDescriptions, vehiclePrices, vehicleSeatsLeft, listener);
            System.out.println("Testing adapter" + myAdapter.toString());
            recView.setAdapter(myAdapter);
            recView.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e){
            e.printStackTrace();
        }

        getCarDescription();
        getElectricCarDescription();
        getMotorcycleDescription();

//        vehicleDescriptions.add("Ed");
//        vehicleDescriptions.add("Mark");
//        vehicleDescriptions.add("Nitu");
//        vehicleDescriptions.add("James");
//        vehicleDescriptions.add("Sanaz");
//        vehicleDescriptions.add("Test");


    }

    private void setOnClickListener() {
        listener = new vehiclesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent goToVehicleProfileActivityIntent = new Intent(getApplicationContext(), VehicleProfileActivity.class);
                goToVehicleProfileActivityIntent.putExtra("IDs", vehicleIDs.get(position).toString());
                startActivity(goToVehicleProfileActivityIntent);
            }
        };
    }

    public void getCarDescription()
    {
        try{
            firestore.collection("AllObjects/AllVehicles/Cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){
                            Log.d("Testing", document.getId() + " => " + document.getData());
                            vehicleIDs.add(document.getId());

                            String brand = document.getData().get("brand").toString();
                            vehicleBrands.add(brand);

                            String vehicleType = document.getData().get("vehicleType").toString();
                            vehicleTypes.add(vehicleType);

                            String vehicleModel = document.getData().get("model").toString();
                            vehicleModels.add(vehicleModel);

                            String vehiclePrice = document.getData().get("basePrice").toString();
                            vehiclePrices.add(vehiclePrice);

                            String vehicleCapacity = document.getData().get("capacity").toString();
                            vehicleSeatsLeft.add(vehicleCapacity);
                        }

                        for(int i = 0; i < vehicleTypes.size(); i++)
                        {
                            System.out.println("Type: " + vehicleTypes.get(i) + ", Brand: " + vehicleBrands.get(i) + ", Model: " + vehicleModels.get(i));
                            System.out.println("Price " + vehiclePrices.get(i) + ", Seats Left: " + vehicleSeatsLeft.get(i));
                            vehicleDescriptions.add("Type: " + vehicleTypes.get(i) + ", Brand: " + vehicleBrands.get(i) + ", Model: " + vehicleModels.get(i));
                        }
//                        for(String descriptions: vehicleDescriptions){
//                            System.out.println(descriptions);
//                        }

                        vehicleBrands.clear();
                        vehicleTypes.clear();
                        vehicleModels.clear();
                        vehiclePrices.clear();
                        vehicleSeatsLeft.clear();

                        myAdapter.updateList(vehicleDescriptions, "descriptions");
                        myAdapter.notifyDataSetChanged();

                    }
                    else{
                        Log.d("Testing", "Error getting documents: ", task.getException());
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getElectricCarDescription()
    {
        try{
            firestore.collection("AllObjects/AllVehicles/ElectricCars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){
                            Log.d("Testing", document.getId() + " => " + document.getData());
                            vehicleIDs.add(document.getId());

                            String brand = document.getData().get("brand").toString();
                            vehicleBrands.add(brand);

                            String vehicleType = document.getData().get("vehicleType").toString();
                            vehicleTypes.add(vehicleType);

                            String vehicleModel = document.getData().get("model").toString();
                            vehicleModels.add(vehicleModel);

                            String vehiclePrice = document.getData().get("basePrice").toString();
                            vehiclePrices.add(vehiclePrice);

                            String vehicleCapacity = document.getData().get("capacity").toString();
                            vehicleSeatsLeft.add(vehicleCapacity);
                        }

                        for(int i = 0; i < vehicleTypes.size(); i++)
                        {
                            System.out.println("Type: " + vehicleTypes.get(i) + ", Brand: " + vehicleBrands.get(i) + ", Model: " + vehicleModels.get(i));
                            System.out.println("Price " + vehiclePrices.get(i) + ", Seats Left: " + vehicleSeatsLeft.get(i));
                            vehicleDescriptions.add("Type: " + vehicleTypes.get(i) + ", Brand: " + vehicleBrands.get(i) + ", Model: " + vehicleModels.get(i));
                        }
//                        for(String descriptions: vehicleDescriptions){
//                            System.out.println(descriptions);
//                        }

                        vehicleBrands.clear();
                        vehicleTypes.clear();
                        vehicleModels.clear();
                        vehiclePrices.clear();
                        vehicleSeatsLeft.clear();

                        myAdapter.updateList(vehicleDescriptions, "descriptions");
                        myAdapter.notifyDataSetChanged();
                    }
                    else{
                        Log.d("Testing", "Error getting documents: ", task.getException());
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getMotorcycleDescription()
    {
        try{
            firestore.collection("AllObjects/AllVehicles/Motorcycle").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){
                            Log.d("Testing", document.getId() + " => " + document.getData());
                            vehicleIDs.add(document.getId());

                            String brand = document.getData().get("brand").toString();
                            vehicleBrands.add(brand);

                            String vehicleType = document.getData().get("vehicleType").toString();
                            vehicleTypes.add(vehicleType);

                            String vehicleModel = document.getData().get("model").toString();
                            vehicleModels.add(vehicleModel);

                            String vehiclePrice = document.getData().get("basePrice").toString();
                            vehiclePrices.add(vehiclePrice);

                            String vehicleCapacity = document.getData().get("capacity").toString();
                            vehicleSeatsLeft.add(vehicleCapacity);
                        }

                        for(int i = 0; i < vehicleTypes.size(); i++)
                        {
                            System.out.println("Type: " + vehicleTypes.get(i) + ", Brand: " + vehicleBrands.get(i) + ", Model: " + vehicleModels.get(i));
                            System.out.println("Price " + vehiclePrices.get(i) + ", Seats Left: " + vehicleSeatsLeft.get(i));
                            vehicleDescriptions.add("Type: " + vehicleTypes.get(i) + ", Brand: " + vehicleBrands.get(i) + ", Model: " + vehicleModels.get(i));
                        }

                        vehicleBrands.clear();
                        vehicleTypes.clear();
                        vehicleModels.clear();
                        vehiclePrices.clear();
                        vehicleSeatsLeft.clear();

//                        for(String descriptions: vehicleDescriptions){
//                            System.out.println(descriptions);
//                        }
                        myAdapter.updateList(vehicleDescriptions, "descriptions");
                        myAdapter.notifyDataSetChanged();
                    }
                    else{
                        Log.d("Testing", "Error getting documents: ", task.getException());
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goBack(View v)
    {
        Intent goBackIntent = new Intent(this, MainActivity.class);
        startActivity(goBackIntent);
        finish();
    }
}
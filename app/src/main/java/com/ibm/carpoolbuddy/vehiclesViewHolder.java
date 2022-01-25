package com.ibm.carpoolbuddy;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class vehiclesViewHolder extends RecyclerView.ViewHolder{
    protected TextView locationText;
    protected TextView descriptionText;
    protected TextView priceText;
    protected TextView seatsLeftText;

    public vehiclesViewHolder(@NonNull View itemView) {
        super(itemView);

        locationText = itemView.findViewById(R.id.locationTextView_vehiclesRowView);
        descriptionText = itemView.findViewById(R.id.descriptionTextView_vehiclesRowView);
        priceText = itemView.findViewById(R.id.priceTextView_vehiclesRowView);
        seatsLeftText = itemView.findViewById(R.id.seatsLeftTextView_vehiclesRowView);
    }

    public void goToVehicleProfileActivity(View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Check", Toast.LENGTH_SHORT);
            }
        });
    }
}

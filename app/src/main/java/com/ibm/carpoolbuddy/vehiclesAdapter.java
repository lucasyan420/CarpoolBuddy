package com.ibm.carpoolbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class vehiclesAdapter extends RecyclerView.Adapter<vehiclesViewHolder> {
    ArrayList<String> locations;
    ArrayList<String> descriptions;
    ArrayList<String> prices;
    ArrayList<String> seatsLeft;

    public vehiclesAdapter(ArrayList inputLocations, ArrayList inputDescriptions, ArrayList inputPrices, ArrayList inputSeatsLeft){
        locations = inputLocations;
        descriptions = inputDescriptions;
        prices = inputPrices;
        seatsLeft = inputSeatsLeft;
    }

    @NonNull
    @Override
    public vehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicles_row_view, parent, false);

        vehiclesViewHolder holder = new vehiclesViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull vehiclesViewHolder holder, int position) {
//        holder.locationText.setText(locations.get(position));
        holder.locationText.setText("N/A");

        holder.descriptionText.setText(descriptions.get(position));
//
//        holder.priceText.setText(prices.get(position));
        holder.priceText.setText("N/A");

//        holder.seatsLeftText.setText(seatsLeft.get(position));
        holder.seatsLeftText.setText("N/A");
    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }
}

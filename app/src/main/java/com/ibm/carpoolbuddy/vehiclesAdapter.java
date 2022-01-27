package com.ibm.carpoolbuddy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibm.carpoolbuddy.databinding.VehiclesRowViewBinding;

import java.util.ArrayList;

public class vehiclesAdapter extends RecyclerView.Adapter<vehiclesViewHolder> {
    ArrayList<String> locations;
    ArrayList<String> descriptions;
    ArrayList<String> prices;
    ArrayList<String> seatsLeft;

    private RecyclerViewClickListener listener;

    public vehiclesAdapter(){

    }

    public vehiclesAdapter(ArrayList inputLocations, ArrayList inputDescriptions, ArrayList inputPrices, ArrayList inputSeatsLeft, RecyclerViewClickListener listener){
        locations = inputLocations;
        descriptions = inputDescriptions;
        prices = inputPrices;
        seatsLeft = inputSeatsLeft;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public vehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicles_row_view, parent, false);

        vehiclesViewHolder holder = new vehiclesViewHolder(myView);

        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToVehicleProfileActivityIntent = new Intent(myView.getContext(), VehicleProfileActivity.class);
//                goToVehicleProfileActivityIntent.putExtra("IDs", vehicleIDs.get(position).toString());
                myView.getContext().startActivity(goToVehicleProfileActivityIntent);
                System.out.println("Test");
            }
        });

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


    public void updateList(ArrayList newList, String listType)
    {
        if(listType.equals("locations")){
            this.locations = newList;
        }
        else if(listType.equals("descriptions")){
            this.descriptions = newList;
        }
        else if(listType.equals("prices")){
            this.prices = newList;
        }
        else if(listType.equals("seatsLeft")){
            this.seatsLeft = newList;
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
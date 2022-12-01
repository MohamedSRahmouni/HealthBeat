package com.example.myapplication.speciality;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.ViewHolder> {

    public List<Speciality> specialities = new ArrayList<>();

    public SpecialityAdapter(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.speciality_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Speciality speciality = specialities.get(position);
        holder.name.setText(speciality.getName());
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.speciality_card_id);
        }
    }

}

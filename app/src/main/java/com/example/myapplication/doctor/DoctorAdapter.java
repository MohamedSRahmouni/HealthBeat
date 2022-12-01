package com.example.myapplication.doctor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.speciality.Speciality;
import com.example.myapplication.speciality.SpecialityAdapter;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{
    public List<Doctor> doctors = new ArrayList<>();

    public DoctorAdapter(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card,parent,false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.firstname.setText(doctor.getUsername());
    }


    public int getItemCount() {
        return doctors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView firstname;

        public ViewHolder(View itemView) {
            super(itemView);
            firstname = (TextView) itemView.findViewById(R.id.doctor_card_id);
        }
    }

}


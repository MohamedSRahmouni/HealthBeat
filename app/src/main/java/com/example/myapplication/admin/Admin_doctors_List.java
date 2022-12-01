package com.example.myapplication.admin;

import static com.example.myapplication.admin.Admin_Specialities_list.LAUNCH_ADD_SPECIALITY_ACTIVITY;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.doctor.Doctor;
import com.example.myapplication.doctor.DoctorAdapter;
import com.example.myapplication.doctor.DoctorDatabaseHandler;
import com.example.myapplication.speciality.Speciality;
import com.example.myapplication.speciality.SpecialityAdapter;
import com.example.myapplication.speciality.SpecialityDatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Admin_doctors_List extends AppCompatActivity {
    private RecyclerView rec;
    private DoctorAdapter doctorAdapter;
    private SpecialityDatabaseHandler databaseHandler;
    public List<Doctor> doctorlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        rec = findViewById(R.id.doctor_recycler_view);
        databaseHandler = new SpecialityDatabaseHandler(this);
        doctorAdapter = new DoctorAdapter(doctorlist);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rec.setLayoutManager(layoutManager);
        rec.setAdapter(doctorAdapter);

        getdoctorlist();

    }

    private void getdoctorlist() {

        List<Doctor> currentSpecialities = databaseHandler.getAllDoctors();

        for (Doctor doctor :
                currentSpecialities) {
            doctorlist.add(doctor);
            doctorAdapter.notifyDataSetChanged();
        }

    }



}
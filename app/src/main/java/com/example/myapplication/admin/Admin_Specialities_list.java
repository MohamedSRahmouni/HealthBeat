package com.example.myapplication.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.speciality.Speciality;
import com.example.myapplication.speciality.SpecialityAdapter;
import com.example.myapplication.speciality.SpecialityDatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Admin_Specialities_list extends AppCompatActivity {

    public static int LAUNCH_ADD_SPECIALITY_ACTIVITY = 0;
    private RecyclerView recyclerView;

    public List<Speciality> specialityList = new ArrayList<>();
    private SpecialityAdapter specialityAdapter;
    private SpecialityDatabaseHandler databaseHandler;

    public FloatingActionButton Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_specialities_list);
        databaseHandler = new SpecialityDatabaseHandler(this);

        recyclerView = (RecyclerView) findViewById(R.id.specialities_recycler_view);
        Button = findViewById(R.id.add_speciality_parent_button);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Specialities_list.this, Admin_AddSpeciality.class);
                startActivityForResult(intent,LAUNCH_ADD_SPECIALITY_ACTIVITY);
            }
        });

//        databaseHandler.addSpeciality(new Speciality("speciality" ));
        specialityAdapter = new SpecialityAdapter(specialityList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(specialityAdapter);

        getSpecialities();

    }

    private void getSpecialities() {

        List<Speciality> currentSpecialities = databaseHandler.getAllSpecialities();

        for (Speciality speciality :
                currentSpecialities) {
            specialityList.add(speciality);
            specialityAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_SPECIALITY_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                getSpecialities();
            }
        }
    }
}
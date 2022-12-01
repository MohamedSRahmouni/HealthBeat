package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.speciality.Speciality;
import com.example.myapplication.speciality.SpecialityDatabaseHandler;

public class Admin_AddSpeciality extends AppCompatActivity {

    public EditText input;
    public Button submitButton;
    private SpecialityDatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_speciality);

        databaseHandler = new SpecialityDatabaseHandler(this);

        input = findViewById(R.id.add_speciality_input);
        submitButton = findViewById(R.id.add_speciality_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = input.getText().toString();
                if(value.length()>0){
                    databaseHandler.addSpeciality(new Speciality(value));
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

    }
}
package com.example.myapplication.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Login_Page;
import com.example.myapplication.R;
import com.example.myapplication.SignUp_page;

public class Doctor_home_page extends AppCompatActivity {


    Button btnlog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        btnlog= findViewById(R.id.button3);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctor_home_page.this, Login_Page.class);
                startActivity(intent);
            }
        });

    }
}
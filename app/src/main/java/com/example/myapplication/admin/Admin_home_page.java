package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.Login_Page;
import com.example.myapplication.R;

public class Admin_home_page extends AppCompatActivity {
    ImageButton btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        btn1 = findViewById(R.id.btnlogout);
        btn2 = findViewById(R.id.btndoctor);
        btn3 = findViewById(R.id.btnspec);
        btn4 = findViewById(R.id.btnrequests);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home_page.this,Admin_doctors_List.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home_page.this,Admin_Specialities_list.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home_page.this, Login_Page.class);
            startActivity(intent);
        }
    });
}
}
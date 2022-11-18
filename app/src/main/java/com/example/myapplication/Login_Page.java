package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_Page extends AppCompatActivity {
    Button btn1;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btn1 = findViewById(R.id.button2);
        btn2 = findViewById(R.id.btnlogin);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

    }

    public void openDashboard(){
        Intent intent =new Intent(this, Dashboard_page.class);
        startActivity(intent);
    }
    public void test()
    {
        Intent i = new Intent(this,SignUp_page.class);
        startActivity(i);

    }
}
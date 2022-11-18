package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    ImageView coeur;
    TextView t1,t2;
    private  Handler mHandler = new Handler();
     int splash_TIme=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        coeur = findViewById(R.id.coeur);
        t1 = findViewById(R.id.hb);
        t2 = findViewById(R.id.copyright);
        coeur.animate().translationY(350).setDuration(1000).setStartDelay(0);
        t1.animate().translationY(-850).setDuration(1000).setStartDelay(0);
        t2.animate().translationY(-100).setDuration(2000).setStartDelay(0);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            test();

        }
    },splash_TIme);
      }
    public void test()
    {
        Intent i = new Intent(this,WelcomePage.class);
        startActivity(i);

    }
}
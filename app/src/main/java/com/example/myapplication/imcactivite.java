package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class imcactivite extends AppCompatActivity {




    TextView mbmidisplay, mbmicategory,mgender;
    Button recalculateimc;
    Intent intent;
    ImageView mimageview;
    String mbmi;
    float intbmi;

    String height;
    String weight ;
    float intheight ,intweight;
    RelativeLayout mbackground;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcactivite);
        ActionBar actionBar = getSupportActionBar();
/*
        assert actionBar != null;
        actionBar.setElevation(0);
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<Font color=\"black\"></font>"));
        actionBar.setTitle("Result");*/


        intent=getIntent();

        mbmidisplay=findViewById(R.id.mbmidisplay);
        mbmicategory=findViewById(R.id.bmicategory);
        mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentlayout);
        mimageview=findViewById(R.id.imageview);
        recalculateimc=findViewById(R.id.recalculateimc);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;

        intbmi=intweight/(intheight*intheight);

        mbmi=Float.toString(intbmi);

        if(intbmi<16)
        {
            mbmicategory.setText("Severe Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
        }
        else if(intbmi<16.9 && intbmi>16)
        {
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<18.4 && intbmi>17)
        {
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }

        else if(intbmi<25 && intbmi>18.4)
        {
            mbmicategory.setText("Normal");
          //  mbackground.setBackgroundColor(Color.YELLOW);
            mimageview.setImageResource(R.drawable.ok);
        }

        else if(intbmi<29 && intbmi>25)
        {
            mbmicategory.setText("Overweight");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }
        else
        {
            mbmicategory.setText("Obese Class I");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);











        recalculateimc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(imcactivite.this,IMCActivity.class);
                startActivity(intent1);
                finish();
            }
        });






}}
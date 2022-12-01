package com.example.myapplication;

import static com.example.myapplication.admin.Admin_Specialities_list.LAUNCH_ADD_SPECIALITY_ACTIVITY;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.User.User;
import com.example.myapplication.User.UserDatabaseHandler;
import com.example.myapplication.admin.Admin_home_page;
import com.example.myapplication.doctor.Doctor;
import com.example.myapplication.doctor.DoctorDatabaseHandler;
import com.example.myapplication.speciality.Speciality;
import com.example.myapplication.speciality.SpecialityAdapter;
import com.example.myapplication.speciality.SpecialityDatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class SignUp_page extends AppCompatActivity {
    public EditText username;
    public EditText email;
    public EditText password;
    public EditText repassword;
    public EditText phone;
    Spinner spinner;
    TextView spec ;
    public List<Speciality> specialityList = new ArrayList<>();
    private SpecialityAdapter specialityAdapter;

    public RadioButton doctor;
    public RadioButton user;


    public RadioGroup radiogroup;
    public Button registerbtn;

    private UserDatabaseHandler databaseHandler;
    private DoctorDatabaseHandler dbdoctor;
    private SpecialityDatabaseHandler specialityDatabaseHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        databaseHandler = new UserDatabaseHandler(this);
        specialityDatabaseHandler = new SpecialityDatabaseHandler(this);
        dbdoctor = new DoctorDatabaseHandler(this);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        doctor = findViewById(R.id.radiodoctor);
        user = findViewById(R.id.radiouser);
        radiogroup = findViewById(R.id.radioGroup);
        registerbtn = findViewById(R.id.registerbtn);
        spinner = findViewById(R.id.spinner);
        phone = findViewById(R.id.phone);
        spec = findViewById(R.id.textView18);



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.isChecked() )
                {
                    String v1 = username.getText().toString();
                    String v2 = email.getText().toString();
                    String v3 = password.getText().toString();
                    String v4 = repassword.getText().toString();
                    String v5 = phone.getText().toString();
                    String v6 = spinner.getSelectedItem().toString();
                    String v7 = doctor.getText().toString();
                    String v8 = user.getText().toString();
                    User user= new User();
                    user.setRole(v8);
                    user.setUsername(v1);
                    user.setPassword(v3);
                    user.setRepassword(v4);
                    user.setEmail(v2);
                    specialityDatabaseHandler.addUser(user);
                    Intent intent1 = new Intent(SignUp_page.this, Login_Page.class);
                    startActivity(intent1);

                } else if (doctor.isChecked()) {
                    String v1 = username.getText().toString();
                    String v2 = email.getText().toString();
                    String v3 = password.getText().toString();
                    String v4 = repassword.getText().toString();
                    String v5 = phone.getText().toString();
                    String v6 = spinner.getSelectedItem().toString();
                    String v7 = doctor.getText().toString();
                    String v8 = user.getText().toString();
                    Doctor doc=new Doctor();
                    doc.setUsername(v1);
                    doc.setEmail(v2);
                    doc.setPassword(v3);
                    doc.setRepassword(v4);
                    doc.setSpeciality(v6);
                    doc.setPhone(v5);
                    doc.setVerified(false);
                    specialityDatabaseHandler.addDoctor(doc);
                    Intent intent = new Intent(SignUp_page.this, Login_Page.class);
                    startActivity(intent);
                }
            }
        });


        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctor();

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user();
            }
        });
    }

    private void getSpecialities() {

        List<Speciality> currentSpecialities = specialityDatabaseHandler.getAllSpecialities();

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
    public void user()
    {
        username.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        repassword.setVisibility(View.VISIBLE);
        phone.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        spec.setVisibility(View.INVISIBLE);
        loadSpinnerData();

    }
    private void loadSpinnerData() {
        SpecialityDatabaseHandler db = new SpecialityDatabaseHandler(getApplicationContext());
        List<Speciality> labels = db.getAllSpecialitiesname();


        // Creating adapter for spinner
        ArrayAdapter<Speciality> dataAdapter = new ArrayAdapter<Speciality>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void doctor()
    {
        username.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        repassword.setVisibility(View.VISIBLE);
        phone.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
        spec.setVisibility(View.VISIBLE);

    }

}
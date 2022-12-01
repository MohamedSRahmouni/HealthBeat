package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.User.UserDatabaseHandler;
import com.example.myapplication.User.User_home_page;
import com.example.myapplication.admin.Admin_home_page;
import com.example.myapplication.doctor.Doctor_home_page;
import com.example.myapplication.speciality.SpecialityDatabaseHandler;

public class Login_Page extends AppCompatActivity {
    Button btn1;
    Button btnlogin;
    EditText email;
    EditText password;
    private UserDatabaseHandler databaseHandler;
    private SpecialityDatabaseHandler specialityDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btn1 = findViewById(R.id.button2);
        btnlogin = findViewById(R.id.btnlogin);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        databaseHandler = new UserDatabaseHandler(this);
        specialityDatabaseHandler = new SpecialityDatabaseHandler(this);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermail = email.getText().toString();
                String userpass = password.getText().toString();
                boolean isDoctor = specialityDatabaseHandler.isDoctor(usermail);

                if (usermail.equals("") || userpass.equals(""))
                    Toast.makeText(Login_Page.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (usermail.equals("admin") && userpass.equals("admin")) {
                        Toast.makeText(Login_Page.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login_Page.this, Admin_home_page.class);
                        startActivity(intent);
                    }}
                if(!isDoctor){
                    boolean checkmailpass = specialityDatabaseHandler.checkUser(usermail, userpass);
                    boolean checkmail = specialityDatabaseHandler.checkUsermail(usermail);
                    if (!checkmail) {
                        Toast.makeText(Login_Page.this, "Wrong Email ! ", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!checkmailpass) {
                        Toast.makeText(Login_Page.this, "Wrong Email Or Password ! ", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(Login_Page.this, Dashboard_page.class);
                    startActivity(intent);
                    return;
                }
                boolean checkmailpass = specialityDatabaseHandler.checkDoctor(usermail, userpass);
                boolean checkmail = specialityDatabaseHandler.checkmail(usermail);
                if (!checkmail) {
                    Toast.makeText(Login_Page.this, "Wrong Email ! ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkmailpass) {
                    Toast.makeText(Login_Page.this, "Wrong Email Or Password ! ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Login_Page.this, Doctor_home_page.class);
                startActivity(intent);



//                else
//                     if (checkmailpass ==true  && result.equals("Doctor")){
//                    Toast.makeText(Login_Page.this, "Sign in Successfull ! ", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Login_Page.this, Doctor_home_page.class);
//                    startActivity(intent);
//                }
//                     else
//                     if (checkmailpass ==true && result.equals("User")    ){
//                         Toast.makeText(Login_Page.this, "Sign in Successfull ! ", Toast.LENGTH_SHORT).show();
//                         Intent intent = new Intent(Login_Page.this, User_home_page.class);
//                         startActivity(intent);
//                     }
//
//                     else
//                     if (checkmail ==false ){
//                         Toast.makeText(Login_Page.this, "Wrong Email ! ", Toast.LENGTH_SHORT).show();
//                     }else {
//                         Toast.makeText(Login_Page.this, "Wrong Password ! ", Toast.LENGTH_SHORT).show();
//
//
//                     }
//            }

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

    }

    public void test() {
        Intent i = new Intent(this, SignUp_page.class);
        startActivity(i);

    }
}
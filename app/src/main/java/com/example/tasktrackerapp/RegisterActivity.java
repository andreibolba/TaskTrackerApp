package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password, repassword,firstName,lastName,phone;
    Button register,goToLogIn;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText) findViewById(R.id.username);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repass);
        firstName=(EditText) findViewById(R.id.fNAme);
        lastName=(EditText) findViewById(R.id.lName);
        phone=(EditText) findViewById(R.id.phone);
        register=(Button) findViewById(R.id.register);
        goToLogIn=(Button) findViewById(R.id.gotologin);

        DB= new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail=email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String fName=firstName.getText().toString();
                String lName=lastName.getText().toString();
                String phoneNumber=phone.getText().toString();

                if(user.equals("")||mail.equals("")||pass.equals("")||repass.equals("")||fName.equals("")||lName.equals("")||phoneNumber.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(Utils.isCorrectEmail(mail)==true) {
                        if (pass.equals(repass)) {
                            switch (Utils.checkPassword(pass)) {
                                case 100:
                                    Boolean checkuser = DB.checkusername(user);
                                    Boolean checkemail = DB.checkEmail(mail);
                                    if (checkuser == false && checkemail == false) {
                                        Boolean insert = DB.insertData(user,fName,lName,phoneNumber,mail,pass);
                                        if (insert == true) {
                                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        if (checkemail == true && checkuser == true) {
                                            Toast.makeText(RegisterActivity.this, "User and email already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        } else if (checkemail == true) {
                                            Toast.makeText(RegisterActivity.this, "Email already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    break;
                                case 101:
                                    Toast.makeText(RegisterActivity.this, "Passwords is not 8 letters long", Toast.LENGTH_SHORT).show();
                                    break;
                                case 102:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains digits", Toast.LENGTH_SHORT).show();
                                    break;
                                case 103:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains big letters", Toast.LENGTH_SHORT).show();
                                    break;
                                case 104:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains low letter letters", Toast.LENGTH_SHORT).show();
                                    break;
                                case 105:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains special charaters", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Incorrent email", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });

    }
}
package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersonalProfileActivity extends AppCompatActivity {

    public static User loggedUser;
    TextView username,email,password,firstName,lastName,phone,fullName;
    Button settings,logout,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        username=(TextView) findViewById(R.id.username);
        email=(TextView) findViewById(R.id.email);
        password=(TextView) findViewById(R.id.password);
        firstName=(TextView) findViewById(R.id.fName);
        lastName=(TextView) findViewById(R.id.lName);
        phone=(TextView) findViewById(R.id.phone);
        fullName=(TextView) findViewById(R.id.titlename);
        settings=(Button) findViewById(R.id.accountSettings);
        logout=(Button)findViewById(R.id.logout);
        back=(Button)findViewById(R.id.backtomainpage);

        username.setText(loggedUser.getUsername());
        email.setText(loggedUser.getEmail());
        password.setText(loggedUser.getPass());
        firstName.setText(loggedUser.getfName());
        lastName.setText(loggedUser.getlName());
        phone.setText(loggedUser.getPhone());
        fullName.setText(loggedUser.getfName()+" "+loggedUser.getlName());

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AccountSettings.class);
                AccountSettings.loggedUser=loggedUser;
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
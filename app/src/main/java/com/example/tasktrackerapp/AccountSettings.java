package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountSettings extends AppCompatActivity {
    public static User loggedUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passPatternSpecial="\" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\"";
    EditText username,email,password, repassword,firstName,lastName,phone;
    Button edit,gotoaccount;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        username=(EditText) findViewById(R.id.usernameSettings);
        email=(EditText) findViewById(R.id.emailSettings);
        password=(EditText) findViewById(R.id.passwordSettings);
        repassword=(EditText) findViewById(R.id.repassSettings);
        firstName=(EditText) findViewById(R.id.fNameSettings);
        lastName=(EditText) findViewById(R.id.lNameSettings);
        phone=(EditText) findViewById(R.id.phoneSettings);
        edit=(Button) findViewById(R.id.edit);
        gotoaccount=(Button) findViewById(R.id.backtoaccount);

        DB= new DBHelper(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AccountSettings.this, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        gotoaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PersonalProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
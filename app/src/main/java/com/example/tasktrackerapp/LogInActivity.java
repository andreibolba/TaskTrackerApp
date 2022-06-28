package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileWriter;
import java.io.IOException;

public class LogInActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin,btnregister;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnregister = (Button) findViewById(R.id.gotoregister);

        DB=new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LogInActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    User loggedUser=DB.getUser(user,pass);

                    if(loggedUser!=null){
                        Toast.makeText(LogInActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        try {
                            FileWriter myWriter = new FileWriter("username.txt");
                            myWriter.write(String.valueOf(username));
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        HomeActivity.loggedUSer=loggedUser;
                        startActivity(intent);
                    }else{
                        Toast.makeText(LogInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
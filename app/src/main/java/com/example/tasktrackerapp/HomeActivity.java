package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public static User loggedUSer;

    TextView helloText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        helloText=(TextView) findViewById(R.id.hello);
        helloText.setText("Hello "+loggedUSer.getUsername());
    }
}
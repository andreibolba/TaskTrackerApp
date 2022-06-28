package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static User loggedUSer;

    DBHelper DB= new DBHelper(this);
    TextView helloText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvTasks);

        ArrayList<Task> tasks = DB.getTasks(loggedUSer.getUsername());
        TaskAdapter adapter = new TaskAdapter(tasks);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
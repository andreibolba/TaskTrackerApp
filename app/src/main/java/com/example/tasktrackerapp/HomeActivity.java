package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static User loggedUSer;

    DBHelper DB= new DBHelper(this);
    TextView helloText;

    FloatingActionButton btnprofile, btnaddtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        btnprofile = (FloatingActionButton) findViewById(R.id.profileScreen);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PersonalProfileActivity.class);
                //PersonalProfileActivity.loggedUSer=loggedUSer;
                startActivity(intent);
            }
        });

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvTasks);

        ArrayList<Task> tasks = DB.getTasks("aurel");
        TaskAdapter adapter = new TaskAdapter(tasks);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
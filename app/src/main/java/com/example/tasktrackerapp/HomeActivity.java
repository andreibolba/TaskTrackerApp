package com.example.tasktrackerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class HomeActivity extends AppCompatActivity {

    public static User loggedUSer;
    public String taskName;
    public String taskDesc;

    DBHelper DB= new DBHelper(this);


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
                PersonalProfileActivity.loggedUser=loggedUSer;
                startActivity(intent);
            }
        });

        btnaddtask = (FloatingActionButton) findViewById(R.id.fab);



        btnaddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        addTask();
                        DB.insertTasks(loggedUSer.getUsername(), taskName, taskDesc);
            }
        });

        //Todo: Aici daca user-ul nu are task-uri crapa
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvTasks);

        ArrayList<Task> tasks = DB.getTasks(loggedUSer.getUsername());
        if(tasks.isEmpty())
        {
            DB.insertTasks(loggedUSer.getUsername(),"one","one");
            TaskAdapter adapter = new TaskAdapter(tasks);
            rvContacts.setAdapter(adapter);
            rvContacts.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            TaskAdapter adapter = new TaskAdapter(tasks);
            rvContacts.setAdapter(adapter);
            rvContacts.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    private void addTask() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myView = inflater.inflate(R.layout.input_new_task, null);
        myDialog.setView(myView);

        AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);

        final EditText task = myView.findViewById(R.id.newTaskName);
        final EditText description = myView.findViewById(R.id.newTaskDescription);
        Button save = myView.findViewById(R.id.saveBtn);
        Button cancel = myView.findViewById(R.id.cancelBtn);

        cancel.setOnClickListener(view -> { dialog.dismiss(); });

        save.setOnClickListener(view -> {
            String mTaskName = task.getText().toString().trim();
            taskName = mTaskName;
            String mTaskDescription = description.getText().toString().trim();
            taskDesc = mTaskDescription;

            if(TextUtils.isEmpty(mTaskName)) //|| DB.checkTitle(mTaskName))
            {
                task.setError("Task required or already exists!");
                return;
            }
            if(TextUtils.isEmpty(mTaskDescription))
            {
                description.setError("Description required!");
                return;
            }
        });

        dialog.show();
    }

}
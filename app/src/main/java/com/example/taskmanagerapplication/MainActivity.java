package com.example.taskmanagerapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapplication.controller.TaskController;
import com.example.taskmanagerapplication.model.Task;
import com.example.taskmanagerapplication.view.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private TaskController taskController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvTasks = findViewById(R.id.rvTasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter();
        rvTasks.setAdapter(taskAdapter);

        taskController = new TaskController(this);
        List<Task> tasks = taskController.getAllTasks();
        taskAdapter.setTasks(tasks);

        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);

        fabAddTask.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddTask.class));
        });
    }
}
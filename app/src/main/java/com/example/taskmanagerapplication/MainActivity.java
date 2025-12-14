package com.example.taskmanagerapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapplication.controller.TaskController;
import com.example.taskmanagerapplication.model.Task;
import com.example.taskmanagerapplication.view.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    private TaskAdapter taskAdapter;
    private TaskController taskController;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvTasks = findViewById(R.id.rvTasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter();
        taskAdapter.setOnTaskClickListener(this);
        rvTasks.setAdapter(taskAdapter);

        taskController = new TaskController(this);
        refreshTasks();

        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);

        fabAddTask.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddTask.class));
        });
    }

    private void refreshTasks() {
        tasks = taskController.getAllTasks();
        taskAdapter.setTasks(tasks);
    }

    @Override
    public void onTaskClick(Task task) {
        showOptionsDialog(task);
    }

    private void showOptionsDialog(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Task Options");
        builder.setItems(new CharSequence[]{"Update", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Update
                        Intent intent = new Intent(MainActivity.this, UpdateTask.class);
                        intent.putExtra("task_id", task.id);
                        startActivity(intent);
                        break;
                    case 1: // Delete
                        deleteTask(task);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void deleteTask(Task task) {
        taskController.deleteTask(task.id);
        refreshTasks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTasks();
    }
}

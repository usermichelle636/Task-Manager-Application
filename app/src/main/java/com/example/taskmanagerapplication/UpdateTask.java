package com.example.taskmanagerapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanagerapplication.controller.TaskController;
import com.example.taskmanagerapplication.model.Task;

import java.util.Arrays;

public class UpdateTask extends AppCompatActivity {
    private EditText etTitle;
    private EditText etDescription;
    private Spinner spIsCompleted;
    private Button btnUpdate;
    private TaskController taskController;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        initViews();
        taskController = new TaskController(this);

        taskId = getIntent().getIntExtra("task_id", -1);
        if (taskId != -1) {
            Task task = taskController.getTaskById(taskId);
            if (task != null) {
                etTitle.setText(task.taskTitle);
                etDescription.setText(task.taskDescription);

                String[] isCompletedOptions = getResources().getStringArray(R.array.is_completed_options);
                int selection = Arrays.asList(isCompletedOptions).indexOf(task.isCompleted);
                spIsCompleted.setSelection(selection);
            }
        }

        btnUpdate.setOnClickListener(view -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            String isCompleted = spIsCompleted.getSelectedItem().toString();
            updateTask(title, description, isCompleted);
        });
    }

    private void updateTask(String title, String description, String isCompleted) {
        boolean success = taskController.updateTask(taskId, title, description, isCompleted);
        if (success) {
            Toast.makeText(this, "Task updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error updating task", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnUpdate = findViewById(R.id.btnUpdate);
        spIsCompleted = findViewById(R.id.spIsCompleted);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.is_completed_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIsCompleted.setAdapter(adapter);
    }
}

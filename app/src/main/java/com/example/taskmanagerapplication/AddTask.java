package com.example.taskmanagerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanagerapplication.controller.TaskController;

public class AddTask extends AppCompatActivity {
    private EditText etTitle;
    private EditText etDescription;
    private Spinner spIsCompleted;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initViews();

        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString();
            if (title.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_title_error), Toast.LENGTH_SHORT).show();
                return;
            }
            String description = etDescription.getText().toString();
            String isCompleted = spIsCompleted.getSelectedItem().toString();
            saveTask(title, description, isCompleted);
        });
    }

    private void saveTask(String title, String description, String isCompleted) {
        TaskController taskController = new TaskController(this);
        boolean success = taskController.addTask(title, description, isCompleted);
        if (success) {
            Toast.makeText(this, getString(R.string.TASK_SAVE), Toast.LENGTH_SHORT).show();
            clearFields();
            showMainActivity();
        } else {
            Toast.makeText(this, getString(R.string.TASK_ERROR), Toast.LENGTH_SHORT).show();
        }
    }

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void clearFields() {
        etTitle.setText("");
        etDescription.setText("");
        spIsCompleted.setSelection(0);
    }
    private void initViews() {
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        IsCompleted();
    }

    private void IsCompleted() {
        spIsCompleted = findViewById(R.id.spIsCompleted);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.is_completed_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIsCompleted.setAdapter(adapter);

    }
}

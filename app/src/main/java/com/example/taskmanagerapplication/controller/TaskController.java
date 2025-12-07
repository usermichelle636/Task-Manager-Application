package com.example.taskmanagerapplication.controller;

import android.content.Context;
import android.util.Log;
import com.example.taskmanagerapplication.model.TaskDao;
import com.example.taskmanagerapplication.model.TaskDatabase;
import com.example.taskmanagerapplication.model.Task;
import java.util.List;

public class TaskController {
    private final TaskDao taskDao;

    public TaskController(Context context) {
        TaskDatabase database = TaskDatabase.getInstance(context);
        taskDao = database.taskDao();
    }

    public boolean addTask(String title, String description, String isCompleted) {
        try {
            Task task = new Task(title, description, isCompleted);
            taskDao.insert(task);
            Log.i("TASK_SAVE", "Task saved successfully");
            return true;
        } catch (Exception e) {
            Log.e("TASK_ERROR", "Error saving task: " + e.getMessage());
            return false;
        }
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

    public void deleteTask(Task task) {
        taskDao.delete(task);
    }
}

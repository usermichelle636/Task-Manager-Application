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

    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    public boolean updateTask(int id, String title, String description, String isCompleted) {
        try {
            Log.d("TASK_UPDATE", "Updating task with id: " + id);
            Task task = taskDao.getTaskById(id);
            if (task != null) {
                Log.d("TASK_UPDATE", "Task found: " + task.taskTitle);
                task.taskTitle = title;
                task.taskDescription = description;
                task.isCompleted = isCompleted;
                taskDao.update(task);
                Log.i("TASK_UPDATE", "Task updated successfully");
                return true;
            } else {
                Log.e("TASK_UPDATE", "Task with id " + id + " not found.");
                return false;
            }
        } catch (Exception e) {
            Log.e("TASK_ERROR", "Error updating task: " + e.getMessage(), e);
            return false;
        }
    }

    public void deleteTask(int id) {
        Task task = taskDao.getTaskById(id);
        if (task != null) {
            taskDao.delete(task);
        }
    }
}

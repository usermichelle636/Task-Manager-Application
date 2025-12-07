package com.example.taskmanagerapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "task_title")
    public String taskTitle;
    @ColumnInfo(name = "task_description")
    public String taskDescription;
    @ColumnInfo(name = "created_at")
    public Long createdAt;
    @ColumnInfo(name = "isCompleted")
    public String isCompleted;

    public Task(String taskTitle, String taskDescription, String isCompleted) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
        this.createdAt = System.currentTimeMillis();
    }
}

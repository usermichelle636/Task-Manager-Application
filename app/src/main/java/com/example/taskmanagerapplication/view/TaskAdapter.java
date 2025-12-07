package com.example.taskmanagerapplication.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapplication.R;
import com.example.taskmanagerapplication.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final List<Task> taskList = new ArrayList<>();

    public void setTasks(List<Task> tasks) {
        taskList.clear();
        if (tasks != null) {
            taskList.addAll(tasks);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.txtTitle.setText(task.taskTitle);
        holder.txtDescription.setText(task.taskDescription);
        holder.txtIsCompleted.setText(task.isCompleted);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String dateString = formatter.format(new Date(task.createdAt));
        holder.txtCreatedAt.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        TextView txtIsCompleted;
        TextView txtCreatedAt;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            txtDescription = itemView.findViewById(R.id.tvDescription);
            txtIsCompleted = itemView.findViewById(R.id.tvIsCompleted);
            txtCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
        }
    }

}

package com.example.tasktracker.service;


import com.example.tasktracker.dto.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    List<Task> getAllTasks(String status);

    boolean deleteTask(Long id);

    Task getTaskById(Long id);

    Task updateTask(Long id, Task task);
}

package com.example.tasktracker.service.impl;

import com.example.tasktracker.dto.Task;
import com.example.tasktracker.entity.TaskEntity;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserServiceImpl userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserServiceImpl userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task createTask(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(task, taskEntity);
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setUser(userService.getUserById(task.getUserId()));
        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public List<Task> getAllTasks(String status, Integer userId) {
        List<TaskEntity> taskEntities = new ArrayList<>();
        if (status != null && !status.isEmpty()) {
            taskEntities = taskRepository.findByStatusAndUser(status, userService.getUserById(userId));
        } else {
            taskEntities = taskRepository.findAllByUser(userService.getUserById(userId));
        }
        List<Task> tasks = taskEntities
                .stream()
                .map(emp -> new Task(
                        emp.getId(),
                        emp.getTitle(),
                        emp.getDescription(),
                        emp.getStatus(),
                        emp.getCreatedAt(),
                        emp.getUser().getId()))
                .collect(Collectors.toList());
        return tasks;
    }

    @Override
    public boolean deleteTask(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).get();
        taskRepository.delete(taskEntity);
        return true;
    }

    @Override
    public Task getTaskById(Long id) {
        TaskEntity taskEntity
                = taskRepository.findById(id).get();
        Task task = new Task();
        BeanUtils.copyProperties(taskEntity, task);
        return task;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        TaskEntity taskEntity
                = taskRepository.findById(id).get();
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setStatus(task.getStatus());
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskRepository.save(taskEntity);
        return task;
    }
}

package com.example.tasktracker.service.impl;

import com.example.tasktracker.dto.Task;
import com.example.tasktracker.entity.TaskEntity;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        TaskEntity taskEntity = new TaskEntity();

        BeanUtils.copyProperties(task, taskEntity);
        taskRepository.save(taskEntity);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        List<TaskEntity> taskEntities
                = taskRepository.findAll();

        List<Task> tasks = taskEntities
                .stream()
                .map(emp -> new Task(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()))
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
        taskEntity.setEmailId(task.getEmailId());
        taskEntity.setFirstName(task.getFirstName());
        taskEntity.setLastName(task.getLastName());

        taskRepository.save(taskEntity);
        return task;
    }
}

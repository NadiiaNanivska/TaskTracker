package com.example.tasktracker.controller;

import com.example.tasktracker.dto.Task;
import com.example.tasktracker.service.TaskService;
//import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task employee) {
        return taskService.createTask(employee);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(@RequestParam(required = false) String status) {
        return  taskService.getAllTasks(status);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteTask(@PathVariable Long id) {
        boolean deleted = false;
        deleted = taskService.deleteTask(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = null;
        task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                                   @RequestBody Task task) {
        task = taskService.updateTask(id, task);
        return ResponseEntity.ok(task);
    }
}

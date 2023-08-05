package com.example.tasktracker.controller;

import com.example.tasktracker.dto.Task;
import com.example.tasktracker.entity.UserEntity;
import com.example.tasktracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){this.userService = userService; }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getTaskById(@PathVariable Integer id) {
        UserEntity user = null;
        user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id,
                                           @RequestBody UserEntity user) {
        user = userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }
}

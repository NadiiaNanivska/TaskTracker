package com.example.tasktracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private Integer userId;

//    public Task(long id, String title, String description, String status, LocalDateTime createdAt) {
//        this.id = id;
//        this.title = title;
//        this.status = status;
//        this.description = description;
//        this.createdAt = createdAt;
//    }
}
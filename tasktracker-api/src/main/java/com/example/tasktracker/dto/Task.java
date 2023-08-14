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
}
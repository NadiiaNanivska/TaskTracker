package com.example.tasktracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private long id;
    private String type;
    private LocalDate date;
    private LocalTime time;
    private String content;
    private Integer userId;
}

package com.example.tasktracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Event {
    private long id;
    private String type;
    private LocalDate date;
    private LocalTime time;
    private String content;

    public Event(long id, String type, LocalDate date, LocalTime time, String content) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.time = time;
        this.content = content;
    }
}

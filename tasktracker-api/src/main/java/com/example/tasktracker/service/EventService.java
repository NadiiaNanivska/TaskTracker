package com.example.tasktracker.service;

import com.example.tasktracker.dto.Event;
import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    List<Event> getAllEvents(String type);

    boolean deleteEvent(Long id);

    Event getEventById(Long id);
}

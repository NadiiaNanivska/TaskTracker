package com.example.tasktracker.controller;

import com.example.tasktracker.dto.Event;
import com.example.tasktracker.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){this.eventService = eventService;}

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("{userId}/events")
    public List<Event> getAllEvents(@RequestParam(required = false) String type, @PathVariable Integer userId) {
        return eventService.getAllEvents(type, userId);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
}

package com.example.tasktracker.service.impl;

import com.example.tasktracker.dto.Event;
import com.example.tasktracker.entity.EventEntity;
import com.example.tasktracker.repository.EventRepository;
import com.example.tasktracker.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserServiceImpl userService;

    public EventServiceImpl(EventRepository eventRepository, UserServiceImpl userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    @Override
    public Event createEvent(Event event) {
        EventEntity eventEntity = new EventEntity();
        BeanUtils.copyProperties(event, eventEntity);
        eventEntity.setUser(userService.getUserById(event.getUserId()));
        eventRepository.save(eventEntity);
        event.setId(eventEntity.getId());
        return event;
    }

    @Override
    public List<Event> getAllEvents(String type, Integer userId) {
        List<EventEntity> eventEntities = new ArrayList<>();
        if (type != null && !type.isEmpty()) {
            eventEntities = eventRepository.findByTypeAndUser(type, userService.getUserById(userId));
        } else {
            eventEntities = eventRepository.findAllByUser(userService.getUserById(userId));
        }
        return eventEntities
                .stream()
                .map(emp -> new Event(
                        emp.getId(),
                        emp.getType(),
                        emp.getDate(),
                        emp.getTime(),
                        emp.getContent(),
                        emp.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteEvent(Long id) {
        EventEntity eventEntity = eventRepository.findById(id).get();
        eventRepository.delete(eventEntity);
        return true;
    }

    @Override
    public Event getEventById(Long id) {
        EventEntity eventEntity = eventRepository.findById(id).get();
        Event event = new Event();
        BeanUtils.copyProperties(eventEntity, event);
        return event;
    }
}

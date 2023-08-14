package com.example.tasktracker.repository;

import com.example.tasktracker.entity.EventEntity;
import com.example.tasktracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByTypeAndUser(String type, UserEntity user);
    List<EventEntity> findAllByUser(UserEntity user);
}

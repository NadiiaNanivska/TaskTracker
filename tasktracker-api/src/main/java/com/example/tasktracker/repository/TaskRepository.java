package com.example.tasktracker.repository;

import com.example.tasktracker.entity.TaskEntity;
import com.example.tasktracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByStatusAndUser(String status, UserEntity user);
    List<TaskEntity> findAllByUser(UserEntity user);
}

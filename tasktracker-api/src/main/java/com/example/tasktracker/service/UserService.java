package com.example.tasktracker.service;

import com.example.tasktracker.dto.Task;
import com.example.tasktracker.dto.user.ChangePhotoRequest;
import com.example.tasktracker.entity.UserEntity;

public interface UserService {
    UserEntity getUserById(Integer id);
    UserEntity updateUser(Integer id, UserEntity user);
    UserEntity updateUserPhoto(Integer id, ChangePhotoRequest request);
}

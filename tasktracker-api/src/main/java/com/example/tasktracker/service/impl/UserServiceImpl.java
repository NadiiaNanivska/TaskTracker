package com.example.tasktracker.service.impl;

import com.example.tasktracker.dto.user.ChangePhotoRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.tasktracker.entity.UserEntity;
import com.example.tasktracker.repository.UserRepository;
import com.example.tasktracker.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity getUserById(Integer id) {
        UserEntity userEntity
                = userRepository.findById(id).get();
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity user) {
        UserEntity userEntity
                = userRepository.findById(id).get();
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRepeatPassword(passwordEncoder.encode(user.getRepeatPassword()));
        userEntity.setPhone(user.getPhone());
        userEntity.setRole(user.getRole());
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public UserEntity updateUserPhoto(Integer id, ChangePhotoRequest request) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setPhoto(request.getPhoto());
        userRepository.save(userEntity);
        return userEntity;
    }
}

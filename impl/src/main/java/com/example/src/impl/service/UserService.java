package com.example.src.impl.service;

import com.example.src.impl.handler.ApiException;
import com.example.src.impl.repository.UserEntity;
import com.example.src.impl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public UserEntity update(UserEntity user, String id) {
        user.setId(id);
        return userRepository.save(user);
    }
}
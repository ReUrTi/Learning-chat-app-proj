package com.example.chat_app.service;

import com.example.chat_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<String> getUserNicknameById(Long id) {
        return userRepository.getUserNicknameById(id);
    }
}

package com.example.chat_app.service;

import com.example.chat_app.model.DTO.InterlocutorDTO;
import com.example.chat_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<String> getUserNicknameById(Long id) {
        return userRepository.getUserNicknameById(id);
    }

    public InterlocutorDTO getChatInfoById(Long authUserId, Long interlocutorId){
        return userRepository.getChatInfoById(authUserId, interlocutorId);
    }
}

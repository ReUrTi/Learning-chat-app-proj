package com.example.chat_app.service;

import com.example.chat_app.model.User;
import com.example.chat_app.model.UserDTO;
import com.example.chat_app.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists"; // Сообщение об ошибке
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setNickname(user.getUsername());
        userRepository.save(user);
        return "User  registered successfully";
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

//    public String getNicknameByUsername(String username) {
//        return userRepository.getNicknameByUsername(username);
//    }

//    public Long getUserIdByUsername(String username) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        return userOptional.map(User::getId).orElse(null);
//    }

    public List<UserDTO> findUsersByNickname(String prefix) {
        return userRepository.findByNicknameLikeIgnoreCase(prefix);
    }

    public UserDTO getUserDTOByUsername(String username) {
        return userRepository.getUserDTOByUsername(username);
    }
}
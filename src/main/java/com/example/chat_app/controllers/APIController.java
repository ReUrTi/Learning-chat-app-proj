package com.example.chat_app.controllers;

import com.example.chat_app.model.UserDTO;
import com.example.chat_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/search")
    public List<UserDTO> searchUsers(@RequestParam String searchInput) {
        return userService.findUsersByNickname(searchInput);
    }


}

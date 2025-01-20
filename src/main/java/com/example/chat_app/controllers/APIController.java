package com.example.chat_app.controllers;

import com.example.chat_app.model.DTO.ChatDTO;
import com.example.chat_app.model.DTO.UserDTO;
import com.example.chat_app.service.AllService;
import com.example.chat_app.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
public class APIController {

    @Autowired
    private AllService allService;

    @Autowired
    private ChatService chatService;

    @GetMapping("/api/search")
    public List<UserDTO> searchUsers(@RequestParam String searchInput) {
        return allService.findUsersByNickname(searchInput);
    }

    @PostMapping("/api/block")
    public String blockUser(@RequestParam Long userId, HttpSession session) {
        Long authUserId = (Long) session.getAttribute("userId");
        if(authUserId == null) return "User is not authenticated.";

        return allService.blockUser(authUserId, userId);
    }

    @DeleteMapping("/api/unblock")
    public String unblockUser(@RequestParam Long userId, HttpSession session) {
        Long authUserId = (Long) session.getAttribute("userId");
        if(authUserId == null) return "User is not authenticated.";

        return allService.deleteBlockedUser(authUserId, userId);
    }

    @PostMapping("/api/create/chat")
    public String createChat(@RequestParam Long userId, @RequestParam Timestamp timestamp, HttpSession session) {
        Long authUserId = (Long) session.getAttribute("userId");
        if(authUserId == null) return "User is not authenticated.";

        return allService.showOrCreateChat(authUserId, userId, timestamp);
    }

//    @GetMapping("/api/chats")
//    public List<ChatDTO> getChats(@RequestParam int page, @RequestParam int limit, HttpSession session) {
//        Long authUserId = (Long) session.getAttribute("userId");
//        if(authUserId == null) return Collections.emptyList(); ;
//        int offset = (page - 1) * limit;
//        List<ChatDTO> chatDTOList = chatService.getChats(authUserId, limit, offset);
//        return chatDTOList;
//    }
}

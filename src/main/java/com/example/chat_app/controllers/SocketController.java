package com.example.chat_app.controllers;

import com.example.chat_app.model.request.ChatsRequest;
import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.ChatsResponse;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.service.response.ChatsResponseService;
//import com.example.chat_app.service.response.MessagesResponseService;
import com.example.chat_app.service.response.MessagesResponseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
public class SocketController {

    @Autowired
    ChatsResponseService chatsResponseService;
    @Autowired
    MessagesResponseService messagesResponseService;
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/ping")
    public void ping(Principal principal) {
        messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/pong", "pong");
    }

    @MessageMapping("/messages/lazy")
    public void getMessagesFrom(@Payload MessagesRequest request, Principal principal) {
        try {
            MessagesResponse response = messagesResponseService.getMessages(request);
            messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/messages", response);
        } catch (Exception e) {
            MessagesResponse errorResponse = new MessagesResponse(null, "Error: " + e.getMessage(), null);
            messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/messages", errorResponse);
        }
    }

    @MessageMapping("/chats/lazy")
    public void getChats(@Payload ChatsRequest request, Principal principal) {
        try {
            ChatsResponse response = chatsResponseService.getChats(request);
            messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/chats", response);
        } catch (Exception e) {
            ChatsResponse errorResponse = new ChatsResponse(null, "Error: " + e.getMessage(), null);
            messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/chats", errorResponse);
        }
    }
}
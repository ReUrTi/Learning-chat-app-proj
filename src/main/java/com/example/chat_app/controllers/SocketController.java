package com.example.chat_app.controllers;

import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.model.Message;
import com.example.chat_app.service.ChatService;
import com.example.chat_app.service.MessageService;
import com.example.chat_app.service.response.MessagesResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocketController {

    @Autowired
    ChatService chatService;

    @Autowired
    MessagesResponseService messagesResponseService;

    @MessageMapping("/ping")
    @SendTo("/topic/pong")
    public String ping() {
        return "pong";
    }

    @MessageMapping("/convo/messages")
    @SendTo("/topic/convo/getMS")
    public MessagesResponse getMessagesFrom(@Payload MessagesRequest request) {
        try {
            return messagesResponseService.getMessagesFrom(request);
        } catch (Exception e) {
            return new MessagesResponse(null, "Error: " + e.getMessage());
        }
    }

    @MessageMapping("/chats/get")
    @SendTo("/topic/chats")
    public String getChats() {
        return "pong";
    }
}
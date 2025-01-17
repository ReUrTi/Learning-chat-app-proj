package com.example.chat_app.controllers;

import com.example.chat_app.model.request.ChatsRequest;
import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.ChatsResponse;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.service.response.ChatsResponseService;
//import com.example.chat_app.service.response.MessagesResponseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

//    @Autowired
//    MessagesResponseService messagesResponseService;

    @Autowired
    ChatsResponseService chatsResponseService;

    @MessageMapping("/ping")
    @SendTo("/topic/pong")
    public String ping() {
        return "pong";
    }

    @MessageMapping("/convo/{chatId}/messages")
    @SendTo("/topic/convo/getMS")
    public MessagesResponse getMessagesFrom(@Payload MessagesRequest request, @PathVariable Long chatId) {
//        try {
//            return messagesResponseService.getMessagesFrom(request, chatId);
//        } catch (Exception e) {
//            return new MessagesResponse(null, "Error: " + e.getMessage());
//        }
        return null;
    }

    @MessageMapping("/chats/get")
    @SendTo("/topic/chats")
    public ChatsResponse getChats(@Payload ChatsRequest request) {
        try {
            return chatsResponseService.getChats(request);
        } catch (Exception e) {
            return new ChatsResponse(null, "Error: " + e.getMessage(), null);
        }
    }
}
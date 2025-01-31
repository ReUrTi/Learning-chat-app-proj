package com.example.chat_app.controllers;

import com.example.chat_app.model.request.ChatsRequest;
import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.ChatsResponse;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.security.CustomUserDetails;
import com.example.chat_app.service.HeartbeatService;
import com.example.chat_app.service.UserActivityService;
import com.example.chat_app.service.response.ChatsResponseService;
import com.example.chat_app.service.response.MessagesResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    @Autowired
    private ChatsResponseService chatsResponseService;
    @Autowired
    private MessagesResponseService messagesResponseService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private UserActivityService userActivityService;
    @Autowired
    private HeartbeatService heartbeatService;

    @MessageMapping("/connect")
    public void setOnline(@AuthenticationPrincipal CustomUserDetails principal) {
        userActivityService.userConnected(principal.getId());
    }

    @MessageMapping("/heartbeat")
    public void heartbeat(@AuthenticationPrincipal CustomUserDetails principal) {
        heartbeatService.updateClientActivity(Long.toString(principal.getId()));
        messagingTemplate.convertAndSendToUser(principal.getUsername(), "/topic/activity", "alive.");
    }

    @MessageMapping("/messages/lazy")
    public void getMessagesFrom(@Payload MessagesRequest request, @AuthenticationPrincipal CustomUserDetails principal) {
        try {
            MessagesResponse response = messagesResponseService.getMessages(request);
            messagingTemplate.convertAndSendToUser(principal.getUsername(), "/topic/messages", response);
        } catch (Exception e) {
            MessagesResponse errorResponse = new MessagesResponse(null, "Error: " + e.getMessage(), null);
            messagingTemplate.convertAndSendToUser(principal.getUsername(), "/topic/messages", errorResponse);
        }
    }

    @MessageMapping("/chats/lazy")
    public void getChats(@Payload ChatsRequest request, @AuthenticationPrincipal CustomUserDetails principal) {
        try {
            ChatsResponse response = chatsResponseService.getChats(request);
            messagingTemplate.convertAndSendToUser(principal.getUsername(), "/topic/chats", response);
        } catch (Exception e) {
            ChatsResponse errorResponse = new ChatsResponse(null, "Error: " + e.getMessage(), null);
            messagingTemplate.convertAndSendToUser(principal.getUsername(), "/topic/chats", errorResponse);
        }
    }
}
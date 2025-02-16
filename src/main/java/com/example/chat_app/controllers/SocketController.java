package com.example.chat_app.controllers;

import com.example.chat_app.model.DTO.JwtUserDTO;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public void setOnline(Principal principal) {
        if (principal instanceof Authentication) {
            JwtUserDTO user = (JwtUserDTO) ((Authentication) principal).getPrincipal();
            userActivityService.userConnected(user.getUserId());
        } else {
            throw new IllegalStateException("Principal is not an instance of Authentication!");
        }
    }

    @MessageMapping("/heartbeat")
    public void heartbeat(Principal principal) {
        if (principal instanceof Authentication) {
            JwtUserDTO user = (JwtUserDTO) ((Authentication) principal).getPrincipal();
            heartbeatService.updateClientActivity(Long.toString(user.getUserId()));
            messagingTemplate.convertAndSendToUser(user.getUsername(), "/topic/activity", "alive.");
        } else {
            throw new IllegalStateException("Principal is not an instance of Authentication!");
        }
    }

    @MessageMapping("/messages/lazy")
    public void getMessagesFrom(@Payload MessagesRequest request, Principal principal) {
        try {
            JwtUserDTO user = extractUserFromPrincipal(principal);

            MessagesResponse response = messagesResponseService.getMessages(request);
            messagingTemplate.convertAndSendToUser(user.getUsername(), "/topic/messages", response);
        } catch (Exception e) {
            MessagesResponse errorResponse = new MessagesResponse(null, "Error: " + e.getMessage(), null);
            JwtUserDTO user = extractUserFromPrincipal(principal);
            messagingTemplate.convertAndSendToUser(user.getUsername(), "/topic/messages", errorResponse);
        }
    }

    @MessageMapping("/chats/lazy")
    public void getChats(@Payload ChatsRequest request, Principal principal) {
        try {
            JwtUserDTO user = extractUserFromPrincipal(principal);

            ChatsResponse response = chatsResponseService.getChats(request);
            messagingTemplate.convertAndSendToUser(user.getUsername(), "/topic/chats", response);
        } catch (Exception e) {
            ChatsResponse errorResponse = new ChatsResponse(null, "Error: " + e.getMessage(), null);
            JwtUserDTO user = extractUserFromPrincipal(principal);
            messagingTemplate.convertAndSendToUser(user.getUsername(), "/topic/chats", errorResponse);
        }
    }
    private JwtUserDTO extractUserFromPrincipal(Principal principal) {
        if (principal instanceof Authentication) {
            Object user = ((Authentication) principal).getPrincipal();
            if (user instanceof JwtUserDTO) {
                return (JwtUserDTO) user;
            } else {
                throw new IllegalStateException("Principal does not contain a valid JwtUserDTO!");
            }
        } else {
            throw new IllegalStateException("Principal is not an instance of Authentication!");
        }
    }
}
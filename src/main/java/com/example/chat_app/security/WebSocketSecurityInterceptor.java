package com.example.chat_app.security;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class WebSocketSecurityInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
            if (sessionAttributes != null) {
                UsernamePasswordAuthenticationToken authentication =
                        (UsernamePasswordAuthenticationToken) sessionAttributes.get("authentication");

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new IllegalArgumentException("Authentication not found in session attributes");
                }
            } else {
                throw new IllegalArgumentException("Session attributes are null");
            }
        }

        return message;
    }
}
package com.example.chat_app.model.response;

import com.example.chat_app.model.Message;
import org.springframework.data.domain.Page;

import java.util.List;

public class MessagesResponse {
    private List<Message> messages;
    private String errorMessage;

    public MessagesResponse(List<Message> messages, String errorMessage) {
        this.messages = messages;
        this.errorMessage = errorMessage;
    }

}
package com.example.chat_app.model.response;

import com.example.chat_app.model.Message;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

public class MessagesResponse {
    private List<Message> messages;
    private String errorMessage;
    private Timestamp lastMessageDate;

    public MessagesResponse(List<Message> messages, String errorMessage, Timestamp lastMessageDate) {
        this.messages = messages;
        this.errorMessage = errorMessage;
        this.lastMessageDate = lastMessageDate;
    }

    public Timestamp getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Timestamp lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
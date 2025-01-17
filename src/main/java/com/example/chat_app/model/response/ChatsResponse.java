package com.example.chat_app.model.response;

import com.example.chat_app.model.DTO.ChatDTO;

import java.sql.Timestamp;
import java.util.List;

public class ChatsResponse {
    private List<ChatDTO> chats;
    private String errorMessage;
    private Timestamp lastChatDate;

    public ChatsResponse(List<ChatDTO> chats, String errorMessage, Timestamp lastChatDate) {
        this.chats = chats;
        this.errorMessage = errorMessage;
        this.lastChatDate = lastChatDate;
    }

    public List<ChatDTO> getChats() {
        return chats;
    }

    public void setChats(List<ChatDTO> chats) {
        this.chats = chats;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Timestamp getLastChatDate() {
        return lastChatDate;
    }

    public void setLastChatDate(Timestamp lastChatDate) {
        this.lastChatDate = lastChatDate;
    }
}

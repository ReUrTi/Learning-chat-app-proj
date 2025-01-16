package com.example.chat_app.model.response;

import com.example.chat_app.model.DTO.ChatDTO;

import java.util.List;

public class ChatsResponse {
    private List<ChatDTO> chats;
    private String errorMessage;

    public ChatsResponse(List<ChatDTO> chats, String errorMessage) {
        this.chats = chats;
        this.errorMessage = errorMessage;
    }

    public List<ChatDTO> getChatDTOList() {
        return chats;
    }

    public void setChatDTOList(List<ChatDTO> chats) {
        this.chats = chats;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

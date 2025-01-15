package com.example.chat_app.model.request;

import java.sql.Timestamp;

public class MessagesRequest {
    private Long chatId;
    private Long limit;
    private Timestamp lastLoadedCreatedAt;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Timestamp getLastLoadedCreatedAt() {
        return lastLoadedCreatedAt;
    }

    public void setLastLoadedCreatedAt(Timestamp lastLoadedCreatedAt) {
        this.lastLoadedCreatedAt = lastLoadedCreatedAt;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    // Геттеры и сеттеры
}
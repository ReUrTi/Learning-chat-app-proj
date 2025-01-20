package com.example.chat_app.model.request;

import java.sql.Timestamp;

public class MessagesRequest {
    private int limit;
    private String lastLoaded;
    private Long chatId;

    public String getLastLoaded() {
        return lastLoaded;
    }

    public int getLimit() {
        return limit;
    }

    public Long getChatId() {
        return chatId;
    }
}
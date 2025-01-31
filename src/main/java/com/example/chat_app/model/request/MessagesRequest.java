package com.example.chat_app.model.request;

import java.sql.Timestamp;
import java.time.Instant;

public class MessagesRequest {
    private int limit;
    private Instant lastLoaded;
    private Long chatId;

    public Instant getLastLoaded() {
        return lastLoaded;
    }

    public int getLimit() {
        return limit;
    }

    public Long getChatId() {
        return chatId;
    }
}
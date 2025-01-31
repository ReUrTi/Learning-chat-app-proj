package com.example.chat_app.model.request;

import java.sql.Timestamp;
import java.time.Instant;

public class ChatsRequest {
    private int limit;
    private Instant lastLoaded;
    private Long userId;

    public Instant getLastLoaded() {
        return lastLoaded;
    }

    public int getLimit() {
        return limit;
    }

    public Long getUserId() {
        return userId;
    }
}

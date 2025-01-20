package com.example.chat_app.model.request;

import java.sql.Timestamp;

public class ChatsRequest {
    private int limit;
    private String lastLoaded;
    private Long userId;

    public String getLastLoaded() {
        return lastLoaded;
    }

    public int getLimit() {
        return limit;
    }

    public Long getUserId() {
        return userId;
    }
}

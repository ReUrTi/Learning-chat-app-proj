package com.example.chat_app.model.request;

import java.sql.Timestamp;

public class ChatsRequest {
    private int limit;
    private String lastLoaded;
    private Long userId;

    public String getLastLoaded() {
        return lastLoaded;
    }

    public void setLastLoaded(String lastLoaded) {
        this.lastLoaded = lastLoaded;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

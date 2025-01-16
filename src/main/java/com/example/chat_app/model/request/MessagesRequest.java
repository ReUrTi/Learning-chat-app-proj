package com.example.chat_app.model.request;

import java.sql.Timestamp;

public class MessagesRequest {
    private int limit;
    private Timestamp lastLoadedCreatedAt;

    public Timestamp getLastLoadedCreatedAt() {
        return lastLoadedCreatedAt;
    }

    public void setLastLoadedCreatedAt(Timestamp lastLoadedCreatedAt) {
        this.lastLoadedCreatedAt = lastLoadedCreatedAt;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


}
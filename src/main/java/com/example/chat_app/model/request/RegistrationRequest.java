package com.example.chat_app.model.request;

import java.sql.Timestamp;
import java.time.Instant;

public class RegistrationRequest {
    private final String username;
    private final String password;
    private final Instant timestamp;

    RegistrationRequest(String username, String password, Instant timestamp){
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}

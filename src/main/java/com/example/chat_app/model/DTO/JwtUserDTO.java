package com.example.chat_app.model.DTO;

import java.util.List;

public class JwtUserDTO {
    private String username;
    private String nickname;
    private Long userId;
    private List<String> roles;

    public JwtUserDTO(String username, String nickname, Long userId, List<String> roles) {
        this.username = username;
        this.nickname = nickname;
        this.userId = userId;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getRoles() {
        return roles;
    }
}

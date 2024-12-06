package com.example.chat_app.model;

public class UserDTO {
    private Long id;
    private String nickname;

    public UserDTO(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
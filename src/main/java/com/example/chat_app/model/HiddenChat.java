package com.example.chat_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hidden_chats")
public class HiddenChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Можно использовать AUTO для выбора стратегии
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    public HiddenChat() {

    }

    public HiddenChat(Long userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public Long getId() {
        return id;
    }
}

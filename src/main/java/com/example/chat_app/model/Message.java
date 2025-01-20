package com.example.chat_app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    public Message() {

    }

    public Message(Long chatId, Long userId, String content, Timestamp createdAt) {
        this.chatId = chatId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Message(Long id, Long chatId, Long userId, String content, Timestamp createdAt, boolean isRead) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }
}
package com.example.chat_app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "private_chats")
public class PrivateChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user1_id", nullable = false)
    private Long user1Id;

    @Column(name = "user2_id", nullable = false)
    private Long user2Id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "last_message_id", nullable = false)
    private Long lastMessageId;

    public PrivateChat() {

    }

    public PrivateChat(Long user1Id, Long user2Id, Timestamp createdAt) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }
}
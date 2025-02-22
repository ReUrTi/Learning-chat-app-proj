package com.example.chat_app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

//    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
//    private List<PrivateChat> user1Chats;
//
//    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
//    private List<PrivateChat> user2Chats;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Message> messages;
//
//    @OneToMany(mappedBy = "blocker", cascade = CascadeType.ALL)
//    private List<BlockedUser> blockedUsers;
//
//    @OneToMany(mappedBy = "blocked", cascade = CascadeType.ALL)
//    private List<BlockedUser> blockers;

    public User() {}

    public User(String username, String password, Instant createdAt) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
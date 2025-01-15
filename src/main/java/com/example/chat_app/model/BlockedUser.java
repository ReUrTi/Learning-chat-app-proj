package com.example.chat_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blocked_users")
public class BlockedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blocker_id", nullable = false)
    private Long blockerId;

    @Column(name = "blocked_id", nullable = false)
    private Long blockedId;

    @Column(name = "blocked_at", nullable = false)
    private LocalDateTime blockedAt = LocalDateTime.now();

    public BlockedUser(Long blockerId, Long blockedId) {
        this.blockerId = blockerId;
        this.blockedId = blockedId;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public Long getBlockedId() {
        return blockedId;
    }

    public Long getBlockerId() {
        return blockerId;
    }
}
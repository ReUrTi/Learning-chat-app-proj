package com.example.chat_app.model.DTO;

import java.sql.Timestamp;

public class ChatDTO {
    private Long chatId;
//    private Timestamp lastUpdated;
    private String lastMessage;
    private Long lastMessageUserId;
    private int unreadCount;
    private Long interlocutorId;
    private String interlocutorNickname;
    private Timestamp lastMessageCreatedAt;

    // Геттеры и сеттеры
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

//    public Timestamp getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public void setLastUpdated(Timestamp lastUpdated) {
//        this.lastUpdated = lastUpdated;
//    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getLastMessageUserId() {
        return lastMessageUserId;
    }

    public void setLastMessageUserId(Long lastMessageUserId) {
        this.lastMessageUserId = lastMessageUserId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public Long getInterlocutorId() {
        return interlocutorId;
    }

    public void setInterlocutorId(Long interlocutorId) {
        this.interlocutorId = interlocutorId;
    }

    public String getInterlocutorNickname() {
        return interlocutorNickname;
    }

    public void setInterlocutorNickname(String interlocutorNickname) {
        this.interlocutorNickname = interlocutorNickname;
    }

    public Timestamp getLastMessageCreatedAt() {
        return lastMessageCreatedAt;
    }

    public void setLastMessageCreatedAt(Timestamp lastMessageCreatedAt) {
        this.lastMessageCreatedAt = lastMessageCreatedAt;
    }
}

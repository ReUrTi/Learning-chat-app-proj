package com.example.chat_app.model.DTO;

import java.sql.Timestamp;

public class ChatDTO {
    private Long chatId;
    private String lastMessage;
    private Long lastMessageUserId;
    private int unreadCount;
    private Long interlocutorId;
    private String interlocutorNickname;
    private Timestamp lastMessageDate;
    private Long lastMessageId;
    private boolean lastMessageIsRead;

    public ChatDTO(Long chatId, String lastMessage, Long lastMessageUserId,
                   int unreadCount, Long interlocutorId, String interlocutorNickname, Timestamp lastMessageDate, Long lastMessageId, boolean lastMessageIsRead){
        this.chatId = chatId;
        this.lastMessage = lastMessage;
        this.lastMessageUserId = lastMessageUserId;
        this.unreadCount = unreadCount;
        this.interlocutorId = interlocutorId;
        this.interlocutorNickname = interlocutorNickname;
        this.lastMessageDate = lastMessageDate;
        this.lastMessageId = lastMessageId;
        this.lastMessageIsRead = lastMessageIsRead;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

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

    public Timestamp getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Timestamp lastMessageCreatedAt) {
        this.lastMessageDate = lastMessageDate;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public boolean isLastMessageIsRead() {
        return lastMessageIsRead;
    }

    public void setLastMessageIsRead(boolean lastMessageIsRead) {
        this.lastMessageIsRead = lastMessageIsRead;
    }
}

package com.example.chat_app.model.DTO;

public class InterlocutorDTO {
    private String nickname;
    private Long blockedByThem;
    private Long blockerThem;
    private Long chatId;

    InterlocutorDTO(String nickname, Long blockedByThem, Long blockerThem, Long chatId){

        this.nickname = nickname;
        this.blockedByThem = blockedByThem;
        this.blockerThem = blockerThem;
        this.chatId = chatId;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getBlockedByThem() {
        return blockedByThem;
    }

    public Long getBlockerThem() {
        return blockerThem;
    }

    public Long getChatId() {
        return chatId;
    }
}

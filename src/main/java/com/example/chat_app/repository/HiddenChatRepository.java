package com.example.chat_app.repository;

import com.example.chat_app.model.HiddenChat;
import com.example.chat_app.model.PrivateChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HiddenChatRepository extends JpaRepository<HiddenChat, Long> {

    @Query("SELECT hc FROM HiddenChat hc WHERE hc.chatId = :chatId AND hc.userId = :userId")
    HiddenChat findHiddenChat(@Param("chatId") Long chatId, @Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM HiddenChat hc WHERE hc.chatId = :chatId AND hc.userId = :userId")
    void deleteHiddenChat(@Param("chatId") Long chatId, @Param("userId") Long userId);
}

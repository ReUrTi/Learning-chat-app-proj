package com.example.chat_app.repository;

import com.example.chat_app.model.PrivateChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<PrivateChat, Long> {

    @Query("SELECT pc FROM PrivateChat pc WHERE (pc.user1Id = :user1Id AND pc.user2Id = :user2Id) OR (pc.user1Id = :user2Id AND pc.user2Id = :user1Id)")
    PrivateChat findOneChat(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
}

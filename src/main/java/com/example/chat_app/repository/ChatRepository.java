package com.example.chat_app.repository;

import com.example.chat_app.model.DTO.ChatDTO;
import com.example.chat_app.model.PrivateChat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ChatRepository extends JpaRepository<PrivateChat, Long> {

    @Query("SELECT pc FROM PrivateChat pc WHERE (pc.user1Id = :user1Id AND pc.user2Id = :user2Id) OR (pc.user1Id = :user2Id AND pc.user2Id = :user1Id)")
    PrivateChat findOneChat(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

//    @Query("SELECT new com.example.chat_app.model.DTO.ChatDTO(a.id, b.content, b.userId, "
//            + "(SELECT COUNT(c) FROM Message c WHERE c.chat.id = a.id AND c.isRead = false), "
//            + "d.id, d.nickname, "
//            + "b.createdAt) "
//            + "FROM PrivateChat a "
//            + "JOIN Message b on b.chatId = a.id "
//            + "JOIN User d ON d.id = CASE WHEN a.user1_id = :userId THEN a.user2_id ELSE a.user1_id END "
//            + "WHERE b.createdAt < :lastLoaded AND (a.user1_id = :userId OR a.user2_id = :userId)"
//            + "ORDER BY b.createdAt DESC")
//    Page<ChatDTO> getChatsByUserIdWithCursor(@Param("userId") Long userId, @Param("lastLoaded") Timestamp lastLoaded, Pageable pageable);
}

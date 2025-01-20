package com.example.chat_app.repository;

import com.example.chat_app.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Long> {

//    @Query("SELECT m FROM Message m WHERE m.chatId = :chatId ORDER BY m.createdAt DESC")
//    List<Message> findByChatId(@Param("chatId") Long chatId, Pageable pageable);
//
//    @Query("SELECT m FROM Message m WHERE m.chatId = :chatId AND m.createdAt < :lastLoadedCreatedAt ORDER BY m.createdAt DESC limit = :limit")
//    List<Message> findByChatIdWithCursor(@Param("chatId") Long chatId,
//                                         @Param("lastLoadedCreatedAt") Timestamp lastLoadedCreatedAt,
//                                         @Param("limit") int limit);

}

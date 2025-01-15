package com.example.chat_app.service;

import com.example.chat_app.model.DTO.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ChatDTO> getChats(Long userId, int limit, int offset) {
        String sql = "SELECT pc.id AS chat_id, pc.last_updated, " +
                "(SELECT m.created_at FROM messages m WHERE m.chat_id = pc.id ORDER BY m.created_at DESC LIMIT 1) AS last_message_created_at, " +
                "(SELECT m.content FROM messages m WHERE m.chat_id = pc.id ORDER BY m.created_at DESC LIMIT 1) AS last_message, " +
                "(SELECT m.user_id FROM messages m WHERE m.chat_id = pc.id ORDER BY m.created_at DESC LIMIT 1) AS last_message_user_id, " +
                "(SELECT COUNT(*) FROM messages m WHERE m.chat_id = pc.id AND m.is_read = false AND m.user_id != ?) AS unread_count, " +
                "(CASE WHEN pc.user1_id = ? THEN pc.user2_id ELSE pc.user1_id END) AS interlocutor_id, " +
                "(SELECT u.nickname FROM users u WHERE u.id = (CASE WHEN pc.user1_id = ? THEN pc.user2_id ELSE pc.user1_id END)) AS interlocutor_nickname " +
                "FROM private_chats pc " +
                "WHERE (pc.user1_id = ? OR pc.user2_id = ?) " +
                "AND pc.id NOT IN (SELECT hc.id FROM hidden_chats hc WHERE hc.user_id = ?) " +
                "ORDER BY pc.last_updated DESC " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new Object[]{userId, userId, userId, userId, userId, userId, limit, offset}, (rs, rowNum) -> {
            ChatDTO chat = new ChatDTO();
            chat.setChatId(rs.getLong("chat_id"));
//            chat.setLastUpdated(rs.getTimestamp("last_updated"));
            chat.setLastMessage(rs.getString("last_message"));
            chat.setLastMessageUserId(rs.getLong("last_message_user_id"));
            chat.setUnreadCount(rs.getInt("unread_count"));
            chat.setInterlocutorId(rs.getLong("interlocutor_id"));
            chat.setInterlocutorNickname(rs.getString("interlocutor_nickname"));
            chat.setLastMessageCreatedAt(rs.getTimestamp("last_message_created_at"));
            return chat;
        });
    }
}

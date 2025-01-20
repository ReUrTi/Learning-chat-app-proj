package com.example.chat_app.service.response;

import com.example.chat_app.model.DTO.ChatDTO;
import com.example.chat_app.model.request.ChatsRequest;
import com.example.chat_app.model.response.ChatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatsResponseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ChatsResponse getChats(ChatsRequest request){
        try{
            Timestamp lastLoadedTimestamp = Timestamp.valueOf(request.getLastLoaded());
            List<ChatDTO> chats = getChatsByUserIdWithCursor(request.getUserId(), lastLoadedTimestamp, request.getLimit());
            Timestamp timestamp = null;
            if(chats != null && !chats.isEmpty()) timestamp = chats.getLast().getLastMessageDate();
            ChatsResponse chatsResponse = new ChatsResponse(chats, null, timestamp);
            return chatsResponse;
        }
        catch (IllegalArgumentException illegalEx) {
            return new ChatsResponse(null, "Bad parrametrs: " + illegalEx.getMessage(), null);
        }catch (Exception e) {
            return new ChatsResponse(null, "Unknown error: " + e.getMessage(), null);
        }
    }

    public List<ChatDTO> getChatsByUserIdWithCursor(Long userId, Timestamp lastLoaded, int limit) {
        String sql = "SELECT a.id AS chatId, b.content, b.user_id as lastMessageUserId, " +
                "CASE WHEN b.user_id = ? THEN 0 " +
                "ELSE (SELECT COUNT(*) FROM messages c WHERE c.chat_id = a.id AND c.is_read = false AND c.user_id != ?) END AS unreadCount, " +
                "CASE WHEN a.user1_id = ? THEN a.user2_id ELSE a.user1_id END AS otherUserId, " +
                "d.nickname, b.created_at, b.id as lastMessageId, b.is_read as isRead " +
                "FROM private_chats a " +
                "JOIN messages b ON b.id = a.last_message_id " +
                "JOIN users d ON d.id = CASE WHEN a.user1_id = ? THEN a.user2_id ELSE a.user1_id END " +
                "LEFT JOIN hidden_chats h on h.chat_id = a.id AND h.user_id = ? " +
                "WHERE b.created_at < ? AND h.chat_id IS NULL AND (a.user1_id = ? OR a.user2_id = ?) " +
                "ORDER BY b.created_at DESC " +
                "LIMIT ?";

        return jdbcTemplate.query(sql, new Object[]{userId, userId, userId, userId, userId, lastLoaded, userId, userId, limit},
                (rs, rowNum) -> new ChatDTO(
                        rs.getLong("chatId"),
                        rs.getString("content"),
                        rs.getLong("lastMessageUserId"),
                        rs.getInt("unreadCount"),
                        rs.getLong("otherUserId"),
                        rs.getString("nickname"),
                        rs.getTimestamp("created_at"),
                        rs.getLong("lastMessageId"),
                        rs.getBoolean("isRead")
                ));
    }
}

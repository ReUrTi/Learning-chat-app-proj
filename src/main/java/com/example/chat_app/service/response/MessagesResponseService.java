package com.example.chat_app.service.response;

import com.example.chat_app.model.DTO.ChatDTO;
import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class MessagesResponseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MessagesResponse getMessages(MessagesRequest request){
        try {
            List<Message> messageList = messages(request.getChatId(), request.getLastLoaded(), request.getLimit());
            Instant timestamp = null;
            if(!messageList.isEmpty()) timestamp = messageList.getLast().getCreatedAt();
            return new MessagesResponse(messageList, null, timestamp);
        } catch (Exception e) {
            return new MessagesResponse(null, "SQL place error: " + e.getMessage(), null);
        }
    }

    private List<Message> messages(Long chatId, Instant timestamp, int limit){
        String sql = "select * from messages m where m.chat_Id = ? and m.created_at < ? order by m.created_at desc limit ?";
        return jdbcTemplate.query(sql, new Object[]{chatId, timestamp, limit},
                (rs, rowNum) -> new Message(
                        rs.getLong("id"),
                        rs.getLong("chat_id"),
                        rs.getLong("user_id"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_read")
                ));
    }

}
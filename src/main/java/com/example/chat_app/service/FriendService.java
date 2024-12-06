//package com.example.chat_app.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//
//@Service
//public class FriendService {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public boolean addFriend(long userId, long friendId) {
//        // Проверяем, существуют ли уже записи о дружбе
//        String checkQuery = "SELECT COUNT(*) FROM friends WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)";
//
//        Integer count = jdbcTemplate.query(checkQuery, new Object[]{userId, friendId, friendId, userId}, (rs, rowNum) -> rs.getInt(1)).stream().findFirst().orElse(0);
//
//        if (count == 0) {
//            // Если записи нет, добавляем новую с статусом "requested"
//            String insertQuery = "INSERT INTO friends (user_id, friend_id, status, timestamp) VALUES (?, ?, ?, ?)";
//            jdbcTemplate.update(insertQuery, userId, friendId, "requested", new Timestamp(System.currentTimeMillis()));
//            return true;
//        } else confirmFriendship(userId, friendId);
//        return false;
//
//    }
//
//    // Метод для изменения статуса дружбы, например, на "confirmed"
//    public void confirmFriendship(long userId, long friendId) {
//        String updateQuery = "UPDATE friends SET status = ? WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)";
//        jdbcTemplate.update(updateQuery, "confirmed", userId, friendId, friendId, userId);
//    }
//}
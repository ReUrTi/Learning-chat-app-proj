package com.example.chat_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserActivityService {

    @Autowired
    @Qualifier("userActivityTemplate")
    private RedisTemplate<String, Long> userActivityTemplate;

    public void userConnected(Long userId) {
        userActivityTemplate.opsForSet().add("online_users", userId);
    }

    public void userDisconnected(Long userId) {
        userActivityTemplate.opsForSet().remove("online_users", userId);
    }

    public Set<Long> getOnlineUsers() {
        return userActivityTemplate.opsForSet().members("online_users");
    }

    public boolean userCheck(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userActivityTemplate.opsForSet().isMember("online_users", userId);
    }
}
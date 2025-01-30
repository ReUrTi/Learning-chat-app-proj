package com.example.chat_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class HeartbeatService {

    private static final long TIMEOUT = 30000;
    private static final long CHECK_INTERVAL = 10000;

    @Autowired
    @Qualifier("heartbeatTemplate")
    private RedisTemplate<String, Long> heartbeatTemplate;

    @Autowired
    private UserActivityService userActivityService;

    public void updateClientActivity(String clientId) {
        heartbeatTemplate.opsForValue().set(clientId, System.currentTimeMillis(), TIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Scheduled(fixedRate = CHECK_INTERVAL)
    public void checkClientActivity() {
        long currentTime = System.currentTimeMillis();
        Set<String> clients = heartbeatTemplate.keys("*");
        if (clients != null) {
            for (String clientId : clients) {
                Long lastSeen = heartbeatTemplate.opsForValue().get(clientId);
                if (lastSeen == null || currentTime - lastSeen > TIMEOUT) {
                    heartbeatTemplate.delete(clientId);
                    userActivityService.userDisconnected(Long.valueOf(clientId));
                }
            }
        }
    }
}
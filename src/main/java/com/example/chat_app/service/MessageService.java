package com.example.chat_app.service;

import com.example.chat_app.model.Message;
import com.example.chat_app.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesByChatId(Long chatId, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return messageRepository.findByChatId(chatId, pageable);
    }

    public List<Message> getMessagesByChatIdWithCursor(Long chatId, Long limit, Timestamp lastLoadedCreatedAt) {
        return messageRepository.findByChatIdWithCursor(chatId, lastLoadedCreatedAt, limit);
    }
}

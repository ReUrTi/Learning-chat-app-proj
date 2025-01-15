package com.example.chat_app.service.response;

import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.model.Message;
import com.example.chat_app.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;

public class MessagesResponseService {
    private static final Logger logger = LoggerFactory.getLogger(MessagesResponseService.class);
    private final MessageService messageService;

    public MessagesResponseService(MessageService messageService) {
        this.messageService = messageService;
    }

    public MessagesResponse getMessagesFrom(MessagesRequest request) {
        try {
            List<Message> messages = messageService.getMessagesByChatIdWithCursor(request.getChatId(), request.getLimit(), request.getLastLoadedCreatedAt());
            return new MessagesResponse(messages, null);
        } catch (IllegalArgumentException illegalEx) {
            logger.warn("Bad parrametrs: {}", illegalEx.getMessage());
            return new MessagesResponse(null, "Bad parrametrs: " + illegalEx.getMessage());
        } catch (Exception e) {
            logger.error("Unknown error", e);
            return new MessagesResponse(null, "Unknown error: " + e.getMessage());
        }
    }
}
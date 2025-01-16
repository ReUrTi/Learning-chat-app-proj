package com.example.chat_app.service.response;

import com.example.chat_app.model.request.MessagesRequest;
import com.example.chat_app.model.response.MessagesResponse;
import com.example.chat_app.model.Message;
import com.example.chat_app.service.MessageService;
import java.util.List;

//public class MessagesResponseService {
//    private final MessageService messageService;
//
//    public MessagesResponseService(MessageService messageService) {
//        this.messageService = messageService;
//    }
//
//    public MessagesResponse getMessagesFrom(MessagesRequest request, Long chatId) {
//        try {
//            List<Message> messages = messageService.getMessagesByChatIdWithCursor(chatId, request.getLimit(), request.getLastLoadedCreatedAt());
//            return new MessagesResponse(messages, null);
//        } catch (IllegalArgumentException illegalEx) {
//            return new MessagesResponse(null, "Bad parrametrs: " + illegalEx.getMessage());
//        } catch (Exception e) {
//            return new MessagesResponse(null, "Unknown error: " + e.getMessage());
//        }
//    }
//}
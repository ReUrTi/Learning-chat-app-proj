package com.example.chat_app.service;

import com.example.chat_app.model.BlockedUser;
import com.example.chat_app.model.PrivateChat;
import com.example.chat_app.model.User;
import com.example.chat_app.model.DTO.UserDTO;
import com.example.chat_app.repository.BlockRepository;
import com.example.chat_app.repository.ChatRepository;
import com.example.chat_app.repository.HiddenChatRepository;
import com.example.chat_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class AllService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private HiddenChatRepository hiddenChatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setNickname(user.getUsername());
        userRepository.save(user);
        return true;
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public String blockUser(Long blocker_id, Long blocked_id) {
        if(Objects.equals(blocked_id, blocker_id)) return "You cannot block yourself.";

        boolean checkIfUserIsBlocked = blockRepository.existsByParams(blocker_id, blocked_id);
        if(checkIfUserIsBlocked) return "User is already blocked.";

        int countBlockedUsers = blockRepository.countAllById(blocker_id);
        if(countBlockedUsers >= 150) return "Limit of 150 has been reached.";

        blockRepository.save(new BlockedUser(blocker_id, blocked_id));

        return "User is blocked.";
    }

    @Transactional
    public String deleteBlockedUser(Long blocker_id, Long blocked_id) {
        if(Objects.equals(blocked_id, blocker_id)) return "You cannot unblock yourself.";
//        if (blockRepository.existsByParams(blocker_id, blocked_id)) {
        blockRepository.deleteByBlockerIdAndBlockedId(blocker_id, blocked_id);
        return "User is no longer blocked.";
//        }
//        return "User was not blocked.";
    }

    @Transactional
    public String showOrCreateChat(Long authUserId, Long userId, Instant timestamp) {
        if(Objects.equals(authUserId, userId)) return "You cannot create chat with yourself (for now).";
        PrivateChat privateChat = chatRepository.findOneChat(authUserId, userId);
        if(privateChat == null) {
            chatRepository.save(new PrivateChat(authUserId, userId, timestamp));
        } else {
            deleteHiddenChat(privateChat.getId(), authUserId);
        }
        return "Chat is available.";
    }

    @Transactional
    public void deleteHiddenChat(Long chatId, Long userId) {
        hiddenChatRepository.deleteHiddenChat(chatId, userId);
    }

    public List<UserDTO> findUsersByNickname(String prefix) {
        return userRepository.findByNicknameLikeIgnoreCase(prefix);
    }

    public UserDTO getUserDTOByUsername(String username) {
        return userRepository.getUserDTOByUsername(username);
    }
}
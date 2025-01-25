package com.example.chat_app.controllers;

import com.example.chat_app.model.DTO.InterlocutorDTO;
import com.example.chat_app.security.CustomUserDetails;
import com.example.chat_app.service.AllService;
import com.example.chat_app.service.BlockedUserService;
import com.example.chat_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private AllService allService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlockedUserService blockedUserService;

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String interlocutorId, Model model, @AuthenticationPrincipal CustomUserDetails principal) {
        String interlocutorNickname = null;
        InterlocutorDTO chatInfo = null;
        model.addAttribute("nickname", principal.getNickname());
        model.addAttribute("authUserId", principal.getId());
        if (interlocutorId != null) {
            if (interlocutorId.matches("\\d+")) {
                interlocutorId = interlocutorId.replaceFirst("^0+(?!$)", "");
                if(!interlocutorId.isEmpty()) {
                    chatInfo = userService.getChatInfoById(principal.getId(), Long.valueOf(interlocutorId));
                }
            }
            else interlocutorId = null;
        }
        model.addAttribute("interlocutorId", interlocutorId);
        model.addAttribute("interlocutorNickname", chatInfo != null ? chatInfo.getNickname() : null);
        model.addAttribute("blockedByThem", chatInfo != null ? chatInfo.getBlockedByThem() : null);
        model.addAttribute("blockedThem", chatInfo != null ? chatInfo.getBlockerThem() : null);
        model.addAttribute("chatId", chatInfo != null ? chatInfo.getChatId() : null);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchInput, Model model, @AuthenticationPrincipal CustomUserDetails principal) {
        model.addAttribute("nickname", principal.getNickname());
        model.addAttribute("authUserId", principal.getId());
        model.addAttribute("searchInput", searchInput);
        return "search";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
        model.addAttribute("nickname", principal.getNickname());
        model.addAttribute("authUserId", principal.getId());
        return "profile";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/index";
    }
}

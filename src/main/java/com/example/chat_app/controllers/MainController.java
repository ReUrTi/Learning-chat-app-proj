package com.example.chat_app.controllers;

import com.example.chat_app.model.DTO.UserDTO;
import com.example.chat_app.service.AllService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private AllService allService;

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String chatId, Model model, HttpSession session, Principal principal) {
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = allService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        model.addAttribute("authUserId", session.getAttribute("userId"));
        if (chatId != null) {
            if (chatId.matches("\\d+")) chatId = chatId.replaceFirst("^0+(?!$)", "");
            else chatId = null;
            if (chatId != null && chatId.isEmpty()) chatId = null;
        }
        model.addAttribute("requestedChatId", chatId);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchInput, Model model, HttpSession session, Principal principal) {
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = allService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        model.addAttribute("authUserId", session.getAttribute("userId"));
        model.addAttribute("searchInput", searchInput);
        return "search";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session, Principal principal) {
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = allService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        model.addAttribute("authUserId", session.getAttribute("userId"));
        return "profile";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/index";
    }
}

package com.example.chat_app.controllers;

import com.example.chat_app.model.User;
import com.example.chat_app.model.UserDTO;
import com.example.chat_app.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session, Principal principal) {
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = userService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchInput, Model model, HttpSession session, Principal principal) {
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = userService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        model.addAttribute("searchInput", searchInput);
        return "search";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session, Principal principal) {
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = userService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        return "profile";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/index";
    }
}

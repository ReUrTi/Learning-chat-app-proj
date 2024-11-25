package com.example.chat_app.controllers;

import com.example.chat_app.model.User;
import com.example.chat_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        if(!model.containsAttribute("nickname"))
            model.addAttribute("nickname", userService.getNicknameByUsername(SecurityContextHolder
                    .getContext().getAuthentication().getName()));
        return "index";
    }

    @GetMapping("/search")
    public String getSearch(Model model) {
        if(!model.containsAttribute("nickname"))
            model.addAttribute("nickname", userService.getNicknameByUsername(SecurityContextHolder
                    .getContext().getAuthentication().getName()));
        return "search";
    }

    @PostMapping("/search")
    public String postSearch(@RequestParam("searchInput") String searchInput, Model model) {
        if(!model.containsAttribute("nickname"))
            model.addAttribute("nickname", userService.getNicknameByUsername(SecurityContextHolder
                    .getContext().getAuthentication().getName()));
        model.addAttribute("searchInput", searchInput);
        return "search";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        if(!model.containsAttribute("nickname"))
            model.addAttribute("nickname", userService.getNicknameByUsername(SecurityContextHolder
                    .getContext().getAuthentication().getName()));
        return "profile";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/index";
    }
}

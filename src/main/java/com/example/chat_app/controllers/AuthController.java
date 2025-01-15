package com.example.chat_app.controllers;

import com.example.chat_app.model.User;
import com.example.chat_app.service.AllService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private AllService allService;

    @GetMapping("/register")
    public String showRegistrationForm(Principal principal) {
        if (principal != null)
            return "index";

        return "register";
    }

    @PostMapping("/register")
    public String registerUser (@RequestParam String username, @RequestParam String password, Model model) {
        String result = allService.register(new User(username, password));
        if (result.equals("Username already exists"))
            model.addAttribute("errorMessage", result);
        else
            model.addAttribute("successMessage", result);
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
//            session.setAttribute("nickname", userService.getNicknameByUsername(principal.getName()));
//            session.setAttribute("userIde", userService.getUserIdByUsername(principal.getName()));
            //model.addAttribute("nickname", session.getAttribute("nickname"));
            return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session, Principal principal) {
        //User authenticatedUser =
        allService.authenticate(username, password);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
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
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Principal principal) {
        if (principal != null)
            return "redirect:/index";

        return "register";
    }

    @PostMapping("/register")
    public String registerUser (@RequestParam String username, @RequestParam String password, Model model) {
        String result = userService.register(new User(username, password));
        if (result.equals("Username already exists"))
            model.addAttribute("errorMessage", result);
        else
            model.addAttribute("successMessage", result);
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, Principal principal) {
        if (principal != null)
            return "redirect:/index";
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
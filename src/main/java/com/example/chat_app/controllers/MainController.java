package com.example.chat_app.controllers;

import com.example.chat_app.model.DTO.UserDTO;
import com.example.chat_app.service.AllService;
import com.example.chat_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private AllService allService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String interlocutorId, Model model, HttpSession session, Principal principal) {
        String interlocutorNickname = null;
        if(session.getAttribute("nickname") == null){
            UserDTO userDTO = allService.getUserDTOByUsername(principal.getName());
            session.setAttribute("nickname", userDTO.getNickname());
            session.setAttribute("userId", userDTO.getId());
        }
        model.addAttribute("nickname", session.getAttribute("nickname"));
        model.addAttribute("authUserId", session.getAttribute("userId"));
        if (interlocutorId != null) {
            if (interlocutorId.matches("\\d+")) {
                interlocutorId = interlocutorId.replaceFirst("^0+(?!$)", "");
                if(!interlocutorId.isEmpty()) {
                    Optional<String> optionalNickname = userService.getUserNicknameById(Long.valueOf(interlocutorId));
                    if (optionalNickname.isPresent()) interlocutorNickname = optionalNickname.get();
                }
            }
            else interlocutorId = null;
        }
        model.addAttribute("interlocutorId", interlocutorId);
        model.addAttribute("interlocutorNickname", interlocutorNickname);
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

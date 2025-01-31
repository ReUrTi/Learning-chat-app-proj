package com.example.chat_app.controllers;

import com.example.chat_app.model.User;
import com.example.chat_app.model.request.RegistrationRequest;
import com.example.chat_app.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AllService allService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody RegistrationRequest registrationRequest) {
        try {
            Boolean result = allService.register(new User(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getTimestamp()));
            if(!result) return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Unknown registration error on server.", HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam String username, @RequestParam String password) {
        try {
            if(allService.authenticate(username, password)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }
}
package com.example.chat_app.controllers;

import com.example.chat_app.model.User;
import com.example.chat_app.model.request.AuthenticationRequest;
import com.example.chat_app.model.request.RegistrationRequest;
import com.example.chat_app.security.DetailsService;
import com.example.chat_app.security.JwtUtil;
import com.example.chat_app.service.AllService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AuthController {

    @Autowired
    private AllService allService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DetailsService detailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody RegistrationRequest registrationRequest) {
        try {
            Boolean result = allService.register(new User(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getTimestamp()));
            if(!result) return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Unknown registration error on server. Error: " + e, HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            final UserDetails userDetails = detailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            response.setHeader("Set-Cookie", "jwtToken=" + jwt + "; HttpOnly; Path=/; SameSite=Strict");

            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed. Error: " + e);
        }
    }
}
package com.muts.treinoSecurity.controller;

import com.muts.treinoSecurity.domain.entity.User;
import com.muts.treinoSecurity.dto.LoginRequest;
import com.muts.treinoSecurity.dto.LoginResponse;
import com.muts.treinoSecurity.dto.UserRequest;
import com.muts.treinoSecurity.infra.security.service.TokenService;
import com.muts.treinoSecurity.repository.UserRepository;
import com.muts.treinoSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(loginRequest.password(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(user.getUsername(), token));
        }

        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
        try {
            if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
                return ResponseEntity.badRequest().body("Email already exists");
            }

            User newUser = userService.createUser(userRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
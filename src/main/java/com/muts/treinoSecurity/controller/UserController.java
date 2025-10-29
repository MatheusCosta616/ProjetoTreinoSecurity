package com.muts.treinoSecurity.controller;

import com.muts.treinoSecurity.domain.entity.User;
import com.muts.treinoSecurity.dto.UserRequest;
import com.muts.treinoSecurity.dto.UserResponse;
import com.muts.treinoSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/email")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestBody String email) {
        UserResponse userResponse = userService.getUserByEmail(email);
        return ResponseEntity.ok(userResponse);
    }
}

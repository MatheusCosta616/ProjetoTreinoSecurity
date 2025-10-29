package com.muts.treinoSecurity.service.impl;


import com.muts.treinoSecurity.domain.entity.User;
import com.muts.treinoSecurity.domain.entity.enums.Role;
import com.muts.treinoSecurity.dto.UserRequest;
import com.muts.treinoSecurity.dto.UserResponse;
import com.muts.treinoSecurity.repository.UserRepository;
import com.muts.treinoSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRequest user) {
        User newUser = new User();
        newUser.setUsername(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Senha encriptada
        newUser.setRole(Role.valueOf(user.getRole()));
        return userRepository.save(newUser);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().name());
        return userResponse;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
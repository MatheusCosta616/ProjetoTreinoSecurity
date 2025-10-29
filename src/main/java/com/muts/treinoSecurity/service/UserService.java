package com.muts.treinoSecurity.service;

import com.muts.treinoSecurity.domain.entity.User;
import com.muts.treinoSecurity.dto.UserRequest;
import com.muts.treinoSecurity.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserRequest user);
    UserResponse getUserByEmail(String email);
    List<User> getAllUsers();
}

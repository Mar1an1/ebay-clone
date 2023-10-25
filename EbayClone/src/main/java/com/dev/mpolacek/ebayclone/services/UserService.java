package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.models.User;
import com.dev.mpolacek.ebayclone.models.dtos.SignUpDto;
import com.dev.mpolacek.ebayclone.models.dtos.LoginDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);
    void delete(Long id);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    SignUpDto signUp(SignUpDto signUpDto);
    LoginDto login(LoginDto loginRequest);
    User findByEmail(String email);
}

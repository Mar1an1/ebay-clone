package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);
    void delete(Long id);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}

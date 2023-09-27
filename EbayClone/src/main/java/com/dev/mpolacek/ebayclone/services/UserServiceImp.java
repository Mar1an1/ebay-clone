package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.exceptions.NotFoundException;
import com.dev.mpolacek.ebayclone.models.User;
import com.dev.mpolacek.ebayclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        userRepository.delete(user.get());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}

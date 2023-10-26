package com.dev.mpolacek.ebayclone.controllers;

import com.dev.mpolacek.ebayclone.models.Item;
import com.dev.mpolacek.ebayclone.models.User;
import com.dev.mpolacek.ebayclone.models.dtos.LoginDto;
import com.dev.mpolacek.ebayclone.models.dtos.SignUpDto;
import com.dev.mpolacek.ebayclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> user = userService.getUserById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Update user profile with the provided data
        user.get().setUsername(updatedUser.getUsername());
        user.get().setEmail(updatedUser.getEmail());

        // Save the updated user
        userService.save(user.get());

        return ResponseEntity.ok(user.get());
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getUserProducts(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        List<Item> products = user.get().getItems();

        return ResponseEntity.ok(products);
    }
}

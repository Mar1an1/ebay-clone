package com.dev.mpolacek.ebayclone.controllers;

import com.dev.mpolacek.ebayclone.config.JwtManager;
import com.dev.mpolacek.ebayclone.models.User;
import com.dev.mpolacek.ebayclone.models.dtos.LoginDto;
import com.dev.mpolacek.ebayclone.models.dtos.SignUpDto;
import com.dev.mpolacek.ebayclone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtManager jwtManager;

    public AuthController(UserService userService, JwtManager jwtManager) {
        this.userService = userService;
        this.jwtManager = jwtManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        SignUpDto signUp = userService.signUp(signUpDto);
        return ResponseEntity.ok("User "+ signUp.getEmail() +" successfully signed up");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        LoginDto login = userService.login(loginDto);
        return ResponseEntity.ok(login.getToken());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody String email) {

        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", user.getId());

        String resetToken = jwtManager.generateToken(claims, user.getUsername());
        //userService.sendResetEmail(email, resetToken);

        return ResponseEntity.ok("Password reset email sent");
    }
}

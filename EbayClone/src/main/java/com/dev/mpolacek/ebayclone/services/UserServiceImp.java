package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.config.JwtManager;
import com.dev.mpolacek.ebayclone.exceptions.NotFoundException;
import com.dev.mpolacek.ebayclone.exceptions.UnauthorizedException;
import com.dev.mpolacek.ebayclone.exceptions.ValidationException;
import com.dev.mpolacek.ebayclone.models.User;
import com.dev.mpolacek.ebayclone.models.dtos.LoginDto;
import com.dev.mpolacek.ebayclone.models.dtos.SignUpDto;
import com.dev.mpolacek.ebayclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;

    @Autowired
    public UserServiceImp(UserRepository userRepository, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, JwtManager jwtManager) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtManager = jwtManager;
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


    public SignUpDto signUp(SignUpDto signUpDto) {

        if (signUpDto.getEmail() == null || signUpDto.getEmail().isEmpty()) {
            throw new ValidationException("email is required.");
        }
        if (signUpDto.getUsername() == null || signUpDto.getUsername().isEmpty()) {
            throw new ValidationException("username is required.");
        }
        if (signUpDto.getPassword() == null || signUpDto.getPassword().isEmpty()) {
            throw new ValidationException("password is required.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());

        User newUser = new User();
        newUser.setEmail(signUpDto.getEmail());
        newUser.setUsername(signUpDto.getUsername());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);


        signUpDto.setStatus("success");
        return signUpDto;
    }


    public LoginDto login(LoginDto loginDto) {

        if (loginDto.getUsername() == null || loginDto.getUsername().isEmpty()) {
            throw new ValidationException("username is required.");
        }
        if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
            throw new ValidationException("password is required.");
        }

        CustomUserDetails userDetails;
        try {
            userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(loginDto.getUsername());

        } catch (UsernameNotFoundException e) {
            throw new UnauthorizedException("User Not Found.");
        }

        if (passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {

            Map<String, Object> claims = new HashMap<>();
            claims.put("username", userDetails.getUsername());
            claims.put("userId", userDetails.getId());
            String subject = userDetails.getUsername();
            String jwt = jwtManager.generateToken(claims, subject);

            LoginDto responseDto = new LoginDto();
            responseDto.setEmail(userDetails.getEmail());
            responseDto.setUsername(userDetails.getUsername());
            responseDto.setStatus("success");
            responseDto.setToken(jwt);

            return responseDto;
        }

        throw new UnauthorizedException("Username or Password does not match.");
    }
}

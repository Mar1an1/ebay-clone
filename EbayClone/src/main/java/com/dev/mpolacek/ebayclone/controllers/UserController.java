package com.dev.mpolacek.ebayclone.controllers;

import com.dev.mpolacek.ebayclone.models.dtos.LoginDto;
import com.dev.mpolacek.ebayclone.models.dtos.SignUpDto;
import com.dev.mpolacek.ebayclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


}

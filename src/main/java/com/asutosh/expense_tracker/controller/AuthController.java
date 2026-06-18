package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.dto.LoginRequestDTO;
import com.asutosh.expense_tracker.dto.LoginResponseDTO;
import com.asutosh.expense_tracker.dto.RegisterRequestDTO;
import com.asutosh.expense_tracker.entity.User;
import com.asutosh.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(

            @Valid
            @RequestBody
            RegisterRequestDTO request

    ) {

        return userService
                .registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(
            @Valid
            @RequestBody
            LoginRequestDTO request
    ) {

        return userService
                .loginUser(request);
    }
}
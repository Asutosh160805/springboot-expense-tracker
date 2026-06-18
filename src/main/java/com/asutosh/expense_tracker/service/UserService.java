package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.dto.LoginRequestDTO;
import com.asutosh.expense_tracker.dto.LoginResponseDTO;
import com.asutosh.expense_tracker.dto.RegisterRequestDTO;
import com.asutosh.expense_tracker.entity.User;
import com.asutosh.expense_tracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(
            RegisterRequestDTO request
    ) {

        if(userRepository
                .findByEmail(request.getEmail())
                .isPresent()) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        return userRepository.save(user);
    }

    public LoginResponseDTO loginUser(
            LoginRequestDTO request
    ) {

        User user =
                userRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if(!matches) {
            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return new LoginResponseDTO(
                token
        );
    }
}
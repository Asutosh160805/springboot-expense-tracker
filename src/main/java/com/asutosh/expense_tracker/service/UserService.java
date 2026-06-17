package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.dto.RegisterRequestDTO;
import com.asutosh.expense_tracker.entity.User;
import com.asutosh.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
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

        // Temporary
        user.setPassword(
                request.getPassword()
        );

        return userRepository.save(user);
    }
}
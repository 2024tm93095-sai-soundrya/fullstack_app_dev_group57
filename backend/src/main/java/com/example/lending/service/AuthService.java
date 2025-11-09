package com.example.lending.service;

import com.example.lending.model.User;
import com.example.lending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User signup(User user) {
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> u.getId() + "|" + u.getRole())
                .orElse(null);
    }
}

package com.example.lending.controller;

import com.example.lending.model.User;
import com.example.lending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Object login(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return new ResponseMessage("Login successful", user.get().getRole(), user.get().getId());
        } else {
            return new ResponseMessage("Invalid credentials", null, null);
        }
    }

    record ResponseMessage(String message, String role, Long userId) {}
}

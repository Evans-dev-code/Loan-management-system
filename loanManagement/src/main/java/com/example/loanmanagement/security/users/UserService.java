package com.example.loanmanagement.security.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        String role = user.getUserId().startsWith("ADM") ? "ADMIN" : "USER";
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User authenticateUser(String userId, String password) {
        User existingUser = userRepository.findByUserId(userId);
        if (existingUser == null || !passwordEncoder.matches(password, existingUser.getPassword())) {
            return null;
        }
        return existingUser;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}

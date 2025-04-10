package com.example.loanmanagement.security.auth;

import com.example.loanmanagement.security.users.User;
import com.example.loanmanagement.security.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }

        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully as " + savedUser.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getUserId(), user.getPassword());

        if (authenticatedUser == null) {
            return ResponseEntity.status(401).body("Invalid user ID or password.");
        }

        return ResponseEntity.ok(authenticatedUser);
    }
}

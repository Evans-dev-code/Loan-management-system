package com.example.loanmanagement.security.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
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

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }
        return ResponseEntity.ok(user);
    }
}

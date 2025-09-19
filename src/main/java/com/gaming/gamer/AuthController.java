package com.gaming.gamer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // A simple DTO to accept username & password
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping
    public String login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "admin".equals(request.getPassword())) {
            return "fake-jwt-token";
        }
        throw new RuntimeException("Invalid username or password");
    }
}

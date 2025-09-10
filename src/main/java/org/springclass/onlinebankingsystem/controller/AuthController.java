package org.springclass.onlinebankingsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springclass.onlinebankingsystem.controller.request.LoginRequest;
import org.springclass.onlinebankingsystem.controller.request.RegisterRequest;
import org.springclass.onlinebankingsystem.controller.response.AuthResponse;
import org.springclass.onlinebankingsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}

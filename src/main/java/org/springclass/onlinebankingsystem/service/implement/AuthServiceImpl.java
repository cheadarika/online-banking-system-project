package org.springclass.onlinebankingsystem.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springclass.onlinebankingsystem.controller.request.LoginRequest;
import org.springclass.onlinebankingsystem.controller.request.RegisterRequest;
import org.springclass.onlinebankingsystem.controller.response.AuthResponse;
import org.springclass.onlinebankingsystem.exception.CustomException;
import org.springclass.onlinebankingsystem.repository.UserRepository;
import org.springclass.onlinebankingsystem.repository.entity.User;
import org.springclass.onlinebankingsystem.service.AuthService;
import org.springclass.onlinebankingsystem.shared.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;


    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        log.info("=====>>> Login Request: {}", loginRequest.getUsernameOrEmail());
        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsernameOrEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new CustomException(403, "Incorrect password!!!");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(), loginRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }

        final String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, userDetails.getUsername());
    }

    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        log.info("=====>>> Register Request: {}", registerRequest);
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new CustomException(404, "Username already exists!");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new CustomException(404, "Email already exists!");
        }
        User user = registerRequest.RegisterUser();
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.createUser(user);

        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, user.getUsername());
    }
}

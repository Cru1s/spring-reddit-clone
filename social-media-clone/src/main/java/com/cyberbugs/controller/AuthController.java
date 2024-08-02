/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.controller;

import com.cyberbugs.config.JwtProvider;
import com.cyberbugs.model.User;
import com.cyberbugs.repository.UserRepository;
import com.cyberbugs.request.LoginRequest;
import com.cyberbugs.response.AuthResponse;
import com.cyberbugs.service.CustomUserDetailsService;
import com.cyberbugs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cruis
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody User user) throws Exception {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new Exception("Email is already exist");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());

        User saveUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Register Success");

        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Login Success");

        return res;
    }

    public Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }

}

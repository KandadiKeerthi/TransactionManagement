package com.example.TransactionManagementSystem.controller;

import com.example.TransactionManagementSystem.entities.UserEntity;
import com.example.TransactionManagementSystem.entities.UserLoginDto;
import com.example.TransactionManagementSystem.repository.UserRepository;
import com.example.TransactionManagementSystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String authenticate(@RequestBody UserLoginDto userLoginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(), userLoginDto.getPassword()
                )
        );

        UserEntity user =  userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow();

        return JwtUtils.generateToken(userLoginDto.getEmail());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserLoginDto userLoginDto){
        UserEntity user = new UserEntity();
        user.setEmail(userLoginDto.getEmail());
        user.setPassword(passwordEncoder.encode(userLoginDto.getPassword()));
        user.setFullName(userLoginDto.getFullName());

        userRepository.save(user);

        return "SUCCESS";
    }
}

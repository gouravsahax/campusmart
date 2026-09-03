package com.campusmart.userservice.controller;

import com.campusmart.userservice.dto.RegisterRequestDTO;
import com.campusmart.userservice.service.JwtService;
import com.campusmart.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal String email){
        return userService.me(email);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO requestDTO, @AuthenticationPrincipal String email){
        return userService.register(requestDTO, email);
    }

    @PostMapping("/create-user")
    public void createUser(@AuthenticationPrincipal String email){
        userService.createUser(email);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@AuthenticationPrincipal String email){
        userService.deleteUser(email);
    }
}

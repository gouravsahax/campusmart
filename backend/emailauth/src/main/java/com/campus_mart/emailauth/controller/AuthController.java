package com.campus_mart.emailauth.controller;

import com.campus_mart.emailauth.dto.LoginResponseDTO;
import com.campus_mart.emailauth.dto.OtpRequestDTO;
import com.campus_mart.emailauth.dto.RegisterRequestDTO;
import com.campus_mart.emailauth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpRequestDTO otpRequestDTO){
        String token = authService.verifyOtp(otpRequestDTO);
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .maxAge(5 * 24 * 60 * 60)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("OTP verified successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody RegisterRequestDTO requestDTO){
        LoginResponseDTO responseDTO = authService.login(requestDTO);
        if (!responseDTO.isVerified()){
            return ResponseEntity.ok().body(Map.of(
                    "msg",responseDTO.getMsg()
            ));
        }
        ResponseCookie cookie = ResponseCookie.from("token", responseDTO.getMsg())
                .httpOnly(true)
                .maxAge(5 * 24 * 60 * 60)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("msg", "Logged in successfully"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logged out successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        authService.deleteAccount(userDetails.getUsername());
        return ResponseEntity.ok("Account deleted");
    }


}

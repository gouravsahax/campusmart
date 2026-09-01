package com.campus_mart.emailauth.service;

import com.campus_mart.emailauth.dto.OtpRequestDTO;
import com.campus_mart.emailauth.dto.RegisterRequestDTO;
import com.campus_mart.emailauth.exception.UserAlreadyExistsException;
import com.campus_mart.emailauth.model.AuthMode;
import com.campus_mart.emailauth.model.AuthModel;
import com.campus_mart.emailauth.repository.AuthRepository;
import com.campus_mart.emailauth.exception.BadCredentialsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final JwtService jwtService;

    public ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO){
        if(authRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User Already Exists!!");
        }
        AuthModel authModel = AuthModel.builder()
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .authMode(AuthMode.LOCAL)
                .verified(false)
                .onboarded(false)
                .build();
        authRepository.save(authModel);
        otpService.generateAndSendOtp(registerRequestDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registration successful. Please verify your email with the OTP.");
    }

    public String verifyOtp(OtpRequestDTO otpRequestDTO){
        return  otpService.verifyOtp(otpRequestDTO);
    }

    public String login(RegisterRequestDTO requestDTO){
        AuthModel authModel = authRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        if(!authModel.isVerified()){
            otpService.generateAndSendOtp(requestDTO.getEmail());
            return "Verification code sent to you email";
        }
        if (!passwordEncoder.matches(requestDTO.getPassword(), authModel.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(requestDTO.getEmail());
        return token;
    }

    @Transactional
    public void deleteAccount(String email) {
        AuthModel authModel = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authRepository.delete(authModel);
    }
}

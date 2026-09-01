package com.campus_mart.emailauth.service;

import com.campus_mart.emailauth.dto.OtpRequestDTO;
import com.campus_mart.emailauth.exception.OtpNotVerifiedException;
import com.campus_mart.emailauth.model.AuthModel;
import com.campus_mart.emailauth.model.OtpModel;
import com.campus_mart.emailauth.repository.AuthRepository;
import com.campus_mart.emailauth.repository.OtpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final AuthRepository authRepository;
    private final JavaMailSender javaMailSender;
    private final JwtService jwtService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void generateAndSendOtp(String email){
        SecureRandom r = new SecureRandom();
        int generatedOtp = 100000 + r.nextInt(900000);
        OtpModel otp = otpRepository.findByEmail(email)
                .orElse(new OtpModel());

        otp.setEmail(email);
        otp.setOtp(Integer.toString(generatedOtp));
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otp.setVerified(false);
        otpRepository.save(otp);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setSubject("CampusMart - Your Signup Verification Code");
        simpleMailMessage.setText(
                "Hello,\n\n" +
                        "Thank you for signing up for CampusMart!\n\n" +
                        "Your verification code is: " + otp + "\n\n" +
                        "This code is valid for 5 minutes.\n" +
                        "Please do not share this code with anyone.\n\n" +
                        "If you did not request this verification, you can safely ignore this email.\n\n" +
                        "Regards,\n" +
                        "CampusMart Team"
        );

        javaMailSender.send(simpleMailMessage);

    }

    @Transactional
    public String verifyOtp(OtpRequestDTO otpRequestDTO) {

        OtpModel otpModel = otpRepository
                .findTopByEmailOrderByExpiryTimeDesc(otpRequestDTO.getEmail())
                .orElseThrow(() -> new OtpNotVerifiedException("OTP not found"));

        if (otpModel.isVerified()) {
            throw new OtpNotVerifiedException("OTP already verified");
        }

        if (otpModel.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new OtpNotVerifiedException("OTP has expired");
        }

        if (!otpModel.getOtp().equals(otpRequestDTO.getOtp().trim())) {
            throw new OtpNotVerifiedException("Invalid OTP");
        }

        otpRepository.delete(otpModel);
        AuthModel authModel = authRepository.findByEmail(otpRequestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        authModel.setVerified(true);

        authRepository.save(authModel);

        return jwtService.generateToken(authModel.getEmail());
    }
}

package com.campus_mart.emailauth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "otpModel")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class OtpModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private String otp;

    private LocalDateTime expiryTime;

    private boolean verified;
}

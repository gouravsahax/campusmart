package com.campus_mart.emailauth.utils;

import com.campus_mart.emailauth.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OtpCleanupScheduler {

    private final OtpRepository otpRepository;

    @Scheduled(fixedRate = 3600000) // every hour
    public void cleanupExpiredOtp() {
        otpRepository.deleteByExpiryTimeBefore(LocalDateTime.now());
    }
}

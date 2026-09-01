package com.campus_mart.emailauth.repository;

import com.campus_mart.emailauth.model.OtpModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface OtpRepository extends JpaRepository<OtpModel, UUID> {
    Optional<OtpModel> findByEmail(String email);
    void deleteByExpiryTimeBefore(LocalDateTime time);
    Optional<OtpModel> findTopByEmailOrderByExpiryTimeDesc(String email);
}

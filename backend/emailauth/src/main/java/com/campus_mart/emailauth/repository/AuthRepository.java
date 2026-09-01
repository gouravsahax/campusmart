package com.campus_mart.emailauth.repository;

import com.campus_mart.emailauth.model.AuthModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<AuthModel, UUID> {
    Optional<AuthModel> findByEmail(String email);
}

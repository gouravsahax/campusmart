package com.campus_mart.emailauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AuthMode authMode;

    private boolean verified;
}
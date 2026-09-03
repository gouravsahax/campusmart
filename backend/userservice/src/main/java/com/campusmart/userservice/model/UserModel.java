package com.campusmart.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @NonNull
    private String email;

    private String image;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "emailVerified")
    private LocalDateTime emailVerified;

    private String phone;

    private String college;

    private String department;

    private boolean onboarded;
}
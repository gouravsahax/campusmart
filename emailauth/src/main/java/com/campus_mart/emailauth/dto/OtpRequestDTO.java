package com.campus_mart.emailauth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String otp;
}
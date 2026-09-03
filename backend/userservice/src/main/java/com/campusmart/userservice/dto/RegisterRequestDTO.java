package com.campusmart.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String image;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "College is required")
    private String college;

    @NotBlank(message = "Department is required")
    private String department;
}
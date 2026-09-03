package com.campusmart.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class MeResponseDTO {
    private String name;
    private String email;
    private String image;
    private LocalDateTime createdAt;
    private String phone;
    private String college;
    private String department;
    private boolean onboarded;
}

package com.campus_mart.emailauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDTO {
    String msg;
    boolean isVerified;
}

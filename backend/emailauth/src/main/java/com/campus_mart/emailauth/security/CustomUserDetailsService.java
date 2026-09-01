package com.campus_mart.emailauth.security;

import com.campus_mart.emailauth.model.AuthModel;
import com.campus_mart.emailauth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthModel authModel = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return User
                .withUsername(authModel.getEmail())
                .password(authModel.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}

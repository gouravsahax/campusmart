package com.campusmart.userservice.security;

import com.campusmart.userservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        if(token != null && jwtService.isTokenValid(token) && SecurityContextHolder.getContext().getAuthentication() == null){
            String email = jwtService.extractEmail(token);
            if(email != null){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        Collections.emptyList()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && !authHeader.trim().isEmpty()){
            if(authHeader.startsWith("Bearer ")){
                return authHeader.substring(7).trim();
            }
            return authHeader.trim();
        }
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if("token".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

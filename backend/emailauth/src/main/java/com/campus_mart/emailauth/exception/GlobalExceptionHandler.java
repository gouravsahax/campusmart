package com.campus_mart.emailauth.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UserDoesNotFoundException.class)
    public ResponseEntity<?> handleUserDoesNotExists(UserDoesNotFoundException ex){
        Map<String , String> res = new HashMap<>();
        res.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(OtpNotVerifiedException.class)
    public ResponseEntity<?> handleOtpNotVerified(OtpNotVerifiedException ex){
        Map<String , String> res = new HashMap<>();
        res.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex){
        Map<String , String> res = new HashMap<>();
        res.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(res);
    }
}

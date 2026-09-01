package com.campus_mart.emailauth.exception;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(String msg){
        super(msg);
    }
}

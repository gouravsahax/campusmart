package com.campus_mart.emailauth.exception;

public class UserDoesNotFoundException extends RuntimeException{
    public UserDoesNotFoundException(String msg){
        super(msg);
    }
}

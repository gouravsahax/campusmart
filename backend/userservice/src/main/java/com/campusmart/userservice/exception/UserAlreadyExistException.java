package com.campusmart.userservice.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(){
        super("User already exist");
    }
}

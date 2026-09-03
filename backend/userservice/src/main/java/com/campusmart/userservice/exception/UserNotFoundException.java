package com.campusmart.userservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User does not found");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}

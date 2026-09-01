package com.campus_mart.emailauth.exception;

public class OtpNotVerifiedException extends RuntimeException{
    public OtpNotVerifiedException(String msg){
        super(msg);
    }
}

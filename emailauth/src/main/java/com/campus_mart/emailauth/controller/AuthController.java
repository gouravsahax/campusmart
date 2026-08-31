package com.campus_mart.emailauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {

    @RequestMapping("/test")
    public String test(){
        return "Hello World";
    }
}

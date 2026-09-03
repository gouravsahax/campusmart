package com.campus_mart.emailauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USERSERVICE")
public interface UserClient {

    @PostMapping("/api/user/create-user")
    void createUser(@RequestHeader("Authorization") String token);

}

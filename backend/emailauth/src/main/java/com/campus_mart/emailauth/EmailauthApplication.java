package com.campus_mart.emailauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class EmailauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailauthApplication.class, args);
	}

}

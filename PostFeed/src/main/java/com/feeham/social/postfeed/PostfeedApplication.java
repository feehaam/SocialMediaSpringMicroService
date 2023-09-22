package com.feeham.social.postfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostfeedApplication.class, args);
	}

}

package com.feeham.social.discovererserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscovererserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscovererserverApplication.class, args);
	}

}

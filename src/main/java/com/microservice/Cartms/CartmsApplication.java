package com.microservice.Cartms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CartmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartmsApplication.class, args);
		
	}

}



package com.bitsmilez.checkoutmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces")
public class CheckoutMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckoutMicroserviceApplication.class, args);
	}

}

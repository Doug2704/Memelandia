package com.candido.memeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MemeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemeserviceApplication.class, args);
	}

}

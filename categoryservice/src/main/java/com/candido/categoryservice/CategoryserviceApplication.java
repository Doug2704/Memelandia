package com.candido.categoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CategoryserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryserviceApplication.class, args);
    }

}

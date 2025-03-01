package com.candido.memeservice.service;

import com.candido.memeservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userservice", url = "http://localhost:8081/api/v1/users")
public interface UserServiceClient {
    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Long id);
}
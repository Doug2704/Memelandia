package com.candido.memeservice.service;

import com.candido.memeservice.dto.CategoryDTO;
import com.candido.memeservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "categoryservice", url = "http://localhost:8083/api/v1/users")
public interface CategoryServiceClient {
    @GetMapping("/{id}")
    CategoryDTO getCategory(@PathVariable Long id);
}
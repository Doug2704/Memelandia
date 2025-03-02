package com.candido.memeservice.service;

import com.candido.memeservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Douglas Candido
 * Interface {@link FeignClient} que fornece um usuário
 */
@FeignClient(name = "userservice", url = "http://localhost:8081/api/v1/users")
public interface UserServiceClient {

    /**
     * Busca um usuário no serviço usuários pelo ID fornecido
     *
     * @param id
     * @return DTO contendo todos os dados de um usuário
     */
    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Long id);
}
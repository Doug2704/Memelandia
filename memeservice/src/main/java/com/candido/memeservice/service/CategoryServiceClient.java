package com.candido.memeservice.service;

import com.candido.memeservice.dto.CategoryDTO;
import com.candido.memeservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Douglas Candido
 * Interface {@link FeignClient} que fornece uma Categoria de memes
 */
@FeignClient(name = "categoryservice", url = "http://localhost:8083/api/v1/category")
public interface CategoryServiceClient {

    /**
     * Busca a categoria no serviço de categorias pelo ID fornecido
     *
     * @param id
     * @return DTO contendo todos os dados de uma categoria de memes
     */
    @GetMapping("/{id}")
    CategoryDTO getCategory(@PathVariable Long id);
}
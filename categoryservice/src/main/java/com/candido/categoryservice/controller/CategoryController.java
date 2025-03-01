package com.candido.categoryservice.controller;

import com.candido.categoryservice.entity.MemeCategory;
import com.candido.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Douglas Candido
 * Classe que fornece endpoints para consultas no banco de dados de categorias de memes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/create")
    public ResponseEntity<MemeCategory> createMemeCategory(@RequestBody MemeCategory category) {
        log.info("Recebida requisicao para criar categoria");

        try {
            MemeCategory createdMemeCategory = categoryService.createMemeCategory(category);
            return new ResponseEntity<>(createdMemeCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Recebida requisicao para buscar categoria com id: {}", id);

        Optional<MemeCategory> retrievedMemeCategory = categoryService.findById(id);
        try {
            if (retrievedMemeCategory.isEmpty()) {
                return new ResponseEntity<>("Categoria de memes inexistente", HttpStatus.OK);
            }
            return new ResponseEntity<>(retrievedMemeCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<MemeCategory>> findAll() {
        log.info("Recebida requisicao para buscar categorias");
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MemeCategory category) {
        log.info("Recebida requisicao para atualizar categoria com id: {}", id);
        Optional<MemeCategory> updatedMemeCategory = categoryService.updateCategory(id, category);

        try {
            if (updatedMemeCategory.isEmpty()) {
                return new ResponseEntity<>("Categoria de memes inexistente", HttpStatus.OK);
            }
            return new ResponseEntity<>(updatedMemeCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

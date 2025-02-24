package com.candido.categoryservice.controller;

import com.candido.categoryservice.entity.MemeCategory;
import com.candido.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Douglas Candido
 * Classe que fornece endpoints para consultas no banco de dados de categorias de memes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<MemeCategory> createMemeCategory(@RequestBody MemeCategory category) {
        try {
            MemeCategory createdMemeCategory = categoryService.createMemeCategory(category);
            return new ResponseEntity<>(createdMemeCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            MemeCategory retrievedMemeCategory = categoryService.findById(id).get();
            return new ResponseEntity<>(retrievedMemeCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<MemeCategory>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MemeCategory> update(@PathVariable Long id, @RequestBody MemeCategory category) {
        try {
            MemeCategory updatedMemeCategory = categoryService.updateCategory(id, category);
            return new ResponseEntity<>(updatedMemeCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

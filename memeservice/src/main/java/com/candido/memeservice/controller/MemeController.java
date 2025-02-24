package com.candido.memeservice.controller;

import com.candido.memeservice.entity.Meme;
import com.candido.memeservice.service.MemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Douglas Candido
 * Classe que fornece endpoints para consultas no banco de dados dos memes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/memes")
public class MemeController {

    private final MemeService memeService;

    @PostMapping("/create")
    public ResponseEntity<Meme> createMeme(@RequestBody Meme meme) {
        try {
            Meme createdMeme = memeService.createMeme(meme);
            return new ResponseEntity<>(createdMeme, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Meme retrievedMeme = memeService.findById(id).get();
            return new ResponseEntity<>(retrievedMeme, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Meme>> findAll() {
        return new ResponseEntity<>(memeService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Meme> update(@PathVariable Long id, @RequestBody Meme meme) {
        try {
            Meme updatedMeme = memeService.updateUser(id, meme);
            return new ResponseEntity<>(updatedMeme, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

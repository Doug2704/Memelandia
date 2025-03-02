package com.candido.memeservice.controller;

import com.candido.memeservice.dto.MemeDTO;
import com.candido.memeservice.entity.Meme;
import com.candido.memeservice.service.MemeService;
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
 * Classe que fornece endpoints para consultas no banco de dados dos memes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/memes")
public class MemeController {

    private static final Logger log = LoggerFactory.getLogger(MemeController.class);
    private final MemeService memeService;

    @PostMapping("/create")
    public ResponseEntity<Meme> createMeme(@RequestBody Meme meme) {
        log.info("Recebida requisicao para criar meme");
        try {
            Meme createdMeme = memeService.createMeme(meme);

            return new ResponseEntity<>(createdMeme, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Recebida requisicao para buscar meme pelo id: {}", id);
        Optional<MemeDTO> retrievedMemeDTO = memeService.findById(id);
        try {
            if (retrievedMemeDTO.isEmpty()) {
                return new ResponseEntity<>("Meme inexistente", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(retrievedMemeDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Meme>> findAll() {
        log.info("Recebida requisicao para buscar todos os memes");
        return new ResponseEntity<>(memeService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Meme meme) {
        log.info("Recebida requisicao para atualizar meme com id: {}", id);
        Optional<MemeDTO> updatedMeme = memeService.updateMeme(id, meme);
        try {
            if (updatedMeme.isEmpty()) {
                return new ResponseEntity<>("Meme inexistente", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedMeme, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

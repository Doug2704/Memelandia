package com.candido.userservice.controller;

import com.candido.userservice.entity.User;
import com.candido.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Douglas Candido
 * Classe que fornece endpoints para consultas no banco de dados.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /**
     * Recebe a requisição e cria o usuário
     *
     * @param user
     * @return usuário criado e {@link HttpStatus#OK}
     * @see UserService#createUser(User)
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Recebida requisicao para criar usuário");
        try {
            User createdUSer = userService.createUser(user);
            return new ResponseEntity<>(createdUSer, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Recebe a requisição e busca usuário
     *
     * @param id
     * @return usuário salvo e {@link HttpStatus#OK}
     * ou resposta de usuário inexistente e {@link HttpStatus#NOT_FOUND}
     * @see UserService#findById(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Recebida requisicao para buscar usuário com id: {}", id);
        Optional<User> retrievedUser = userService.findById(id);
        try {
            if (retrievedUser.isEmpty()) {
                return new ResponseEntity<>("Usuário inexistente", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Recebe a requisição e busca todos os usuários
     *
     * @return lista de usuários e {@link HttpStatus#OK}
     * @see UserService#findAll()
     */
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    /**
     * Recebe a requisição e atualiza usuário caso exista no banco de dados
     *
     * @param id
     * @param user
     * @return usuário atualizado e {@link HttpStatus#OK}
     * ou resposta de usuário inexistente e {@link HttpStatus#NOT_FOUND}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
        log.info("Recebida requisicao para atualizar usuario com id: {}", id);
        try {
            Optional<User> updatedUser = userService.updateUser(id, user);
            if (updatedUser.isEmpty()) {
                return new ResponseEntity<>("Usuario inexistente", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

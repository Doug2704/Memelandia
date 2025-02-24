package com.candido.userservice.controller;

import com.candido.userservice.entity.User;
import com.candido.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Douglas Candido
 * Classe que fornece endpoints para consultas no banco de dados.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUSer = userService.createUser(user);
            return new ResponseEntity<>(createdUSer, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            User retrievedUser = userService.findById(id).get();
            return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

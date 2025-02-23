package com.candido.userservice.service;

import com.candido.userservice.entity.User;
import com.candido.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Douglas Candido
 * Classe de serviço para o banco de dados de usuário
 * Contém apenas operações de criar, atualizar e consultar usuários.
 * Como segurança, para deletar um usuário é necessário realizar manualmente no banco de dados.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        Optional<User> retrievedUser = userRepository.findById(id);
        try {
            return userRepository.findById(id);
        } catch (RuntimeException e) {
            if (retrievedUser.isEmpty()) {
                throw new RuntimeException("Usuário inexistente");
            }
            throw e;
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        Optional<User> retrievedUser = userRepository.findById(id);
        try {
            User newUser = retrievedUser.get();
            newUser.setNome(user.getNome());
            newUser.setEmail(user.getEmail());
            return userRepository.save(newUser);
        } catch (RuntimeException e) {
            if (retrievedUser.isEmpty()) {
                throw new RuntimeException("Usuário inexistente");
            }
            throw e;
        }
    }
}

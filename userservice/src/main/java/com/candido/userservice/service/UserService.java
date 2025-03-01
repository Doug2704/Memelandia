package com.candido.userservice.service;

import com.candido.userservice.entity.User;
import com.candido.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        try {
            user.setDataCadastro(Date.valueOf(LocalDate.now()));
            log.info("Salvando usuario no banco de dados...");
            long startTime = System.currentTimeMillis();
            User savedUser = userRepository.save(user);

            long endTime = System.currentTimeMillis();
            log.info("Usuario criado com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return savedUser;

        } catch (RuntimeException e) {
            log.error("Erro ao salvar meme: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<User> findById(Long id) {
        log.info("Buscando usuario com id: {}...", id);
        long startTime = System.currentTimeMillis();
        Optional<User> retrievedUser = userRepository.findById(id);

        try {
            if (retrievedUser.isPresent()) {
                long endTime = System.currentTimeMillis();
                log.info("Usuario retornado com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
                return retrievedUser;
            }
            long endTime = System.currentTimeMillis();
            log.error("Usuario inexistente. Tempo de processamento: {}ms", (endTime - startTime));
            return retrievedUser;
        } catch (RuntimeException e) {
            log.error("Erro ao buscar usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<User> findAll() {
        try {
            log.info("Buscando usuarios...");
            long startTime = System.currentTimeMillis();
            List<User> users = userRepository.findAll();

            long endTime = System.currentTimeMillis();
            log.info("Usuarios retornados com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return users;
        } catch (RuntimeException e) {
            log.error("Erro ao buscar usuarios: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public Optional<User> updateUser(Long id, User user) {
        log.info("buscando usuario com id: {}...", id);
        long startTime = System.currentTimeMillis();
        Optional<User> retrievedUser = userRepository.findById(id);

        try {
            if (retrievedUser.isPresent()) {
                User newUser = retrievedUser.get();

                if (user.getNome() != null) {
                    newUser.setNome(user.getNome());
                }
                if (user.getEmail() != null) {
                    newUser.setEmail(user.getEmail());
                }
                User savedUser = userRepository.save(newUser);

                long endTime = System.currentTimeMillis();
                log.info("Usuario atualizado com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
                return Optional.of(savedUser);
            }

            long endTime = System.currentTimeMillis();
            log.error("Usuario inexistente. Tempo de processamento: {}ms", (endTime - startTime));
            return retrievedUser;

        } catch (RuntimeException e) {
            log.error("Erro ao buscar usuario: {}", e.getMessage(), e);
            throw e;
        }
    }
}
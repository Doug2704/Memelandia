package com.candido.memeservice.service;

import com.candido.memeservice.dto.CategoryDTO;
import com.candido.memeservice.dto.MemeDTO;
import com.candido.memeservice.dto.MemeMapper;
import com.candido.memeservice.dto.UserDTO;
import com.candido.memeservice.entity.Meme;
import com.candido.memeservice.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Douglas Candido
 * Classe de serviço para o banco de dados de memes.
 * Contém apenas operações de criar, atualizar e consultar memes.
 * Como segurança, para deletar um meme é necessário realizar manualmente no banco de dados.
 */
@Service
@Repository
@RequiredArgsConstructor
public class MemeService {
    private final MemeRepository memeRepository;
    private final UserServiceClient userServiceClient;
    private final CategoryServiceClient categoryServiceClient;
    private static final Logger log = LoggerFactory.getLogger(MemeService.class);

    public Meme createMeme(Meme meme) {
        try {
            meme.setDataCadastro(Date.valueOf(LocalDate.now()));
            log.info("Salvando meme no banco de dados...");
            long startTime = System.currentTimeMillis();
            Meme savedMeme = memeRepository.save(meme);

            long endTime = System.currentTimeMillis();
            log.info("Meme criado com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return savedMeme;

        } catch (RuntimeException e) {
            log.error("Erro ao salvar meme: {}", e.getMessage(), e);
            throw e;
        }
    }

    public MemeDTO findById(Long id) {
        log.info("Buscando meme com id: {}...", id);
        long startTime = System.currentTimeMillis();
        Meme retrievedMeme = memeRepository.findById(id).orElseThrow(() -> new RuntimeException("Meme inexistente"));
        try {
            UserDTO userDTO = userServiceClient.getUser(retrievedMeme.getIdUser());
            CategoryDTO categoryDTO = categoryServiceClient.getCategory(retrievedMeme.getIdCategory());
            long endTime = System.currentTimeMillis();
            log.info("Meme retornado com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return MemeMapper.toDto(retrievedMeme, categoryDTO, userDTO);
        } catch (RuntimeException e) {
            log.error("Erro ao buscar meme: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Meme> findAll() {
        log.info("Buscando memes no banco de dados...");
        long startTime = System.currentTimeMillis();
        List<Meme> Memes = memeRepository.findAll();
        long endTime = System.currentTimeMillis();
        log.info("Memes retornados com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
        return Memes;
    }

    public Meme updateMeme(Long id, Meme meme) {

        Optional<Meme> retrievedMeme = memeRepository.findById(id);
        try {
            log.info("Atualizando meme com id: {}...", id);
            long startTime = System.currentTimeMillis();
            Meme newMeme = retrievedMeme.get();

            if (meme.getNome() != null) {
                newMeme.setNome(meme.getNome());
            }
            if (meme.getDescricao() != null) {
                newMeme.setDescricao(meme.getDescricao());
            }
            if (meme.getIdCategory() != null) {
                newMeme.setIdCategory(meme.getIdCategory());
            }
            Meme savedMeme = memeRepository.save(newMeme);
            long endTime = System.currentTimeMillis();
            log.info("Meme atualizado com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return savedMeme;
        } catch (RuntimeException e) {
            if (retrievedMeme.isEmpty()) {
                log.error("Erro ao atualizar meme: {}", e.getMessage(), e);
                throw new RuntimeException("Meme inexistente");
            }
            log.error("Erro ao atualizar meme: {}", e.getMessage(), e);
            throw e;
        }
    }
}

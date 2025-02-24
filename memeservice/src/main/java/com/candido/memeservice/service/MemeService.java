package com.candido.memeservice.service;

import com.candido.memeservice.entity.Meme;
import com.candido.memeservice.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

    public Meme createMeme(Meme meme) {
        return memeRepository.save(meme);
    }

    public Optional<Meme> findById(Long id) {
        Optional<Meme> retrievedMeme = memeRepository.findById(id);

        try {
            return memeRepository.findById(id);
        } catch (RuntimeException e) {
            if (retrievedMeme.isEmpty()) {
                throw new RuntimeException("Meme inexistente");
            }
            throw e;
        }
    }

    public List<Meme> findAll() {
        return memeRepository.findAll();
    }

    public Meme updateUser(Long id, Meme meme) {
        Optional<Meme> retrievedMeme = memeRepository.findById(id);
        try {
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

            return memeRepository.save(newMeme);
        } catch (RuntimeException e) {
            if (retrievedMeme.isEmpty()) {
                throw new RuntimeException("Meme inexistente");
            }
            throw e;
        }
    }
}

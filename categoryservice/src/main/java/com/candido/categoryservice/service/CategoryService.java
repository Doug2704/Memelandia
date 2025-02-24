package com.candido.categoryservice.service;

import com.candido.categoryservice.entity.MemeCategory;
import com.candido.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Douglas Candido
 * Classe de serviço para o banco de dados de categoria de memes
 * Contém apenas operações de criar, atualizar e consultar dados das categorias.
 * Como segurança, para deletar uma categoria é necessário realizar manualmente no banco de dados.
 */
@Service
@Repository
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public MemeCategory createMemeCategory(MemeCategory memeCategory) {
        return categoryRepository.save(memeCategory);
    }

    public Optional<MemeCategory> findById(Long id) {
        Optional<MemeCategory> retrievedMemeCategory = categoryRepository.findById(id);

        try {
            return categoryRepository.findById(id);
        } catch (RuntimeException e) {
            if (retrievedMemeCategory.isEmpty()) {
                throw new RuntimeException("Categoria de memes inexistente");
            }
            throw e;
        }
    }

    public List<MemeCategory> findAll() {
        return categoryRepository.findAll();
    }

    public MemeCategory updateCategory(Long id, MemeCategory memeCategory) {
        Optional<MemeCategory> retrievedMemeCategory = categoryRepository.findById(id);
        try {
            MemeCategory newMemeCategory = retrievedMemeCategory.get();

            if (memeCategory.getNome() != null) {
                newMemeCategory.setNome(memeCategory.getNome());
            }
            if (memeCategory.getDescricao() != null) {
                newMemeCategory.setDescricao(memeCategory.getDescricao());
            }
            return categoryRepository.save(newMemeCategory);
        } catch (RuntimeException e) {
            if (retrievedMemeCategory.isEmpty()) {
                throw new RuntimeException("Categoria de meme inexistente");
            }
            throw e;
        }
    }
}

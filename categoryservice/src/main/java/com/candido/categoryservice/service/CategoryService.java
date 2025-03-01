package com.candido.categoryservice.service;

import com.candido.categoryservice.entity.MemeCategory;
import com.candido.categoryservice.repository.CategoryRepository;
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
 * Classe de serviço para o banco de dados de categoria de memes
 * Contém apenas operações de criar, atualizar e consultar dados das categorias.
 * Como segurança, para deletar uma categoria é necessário realizar manualmente no banco de dados.
 */
@Service
@Repository
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    public MemeCategory createMemeCategory(MemeCategory memeCategory) {
        try {
            memeCategory.setDataCadastro(Date.valueOf(LocalDate.now()));
            log.info("Salvando categoria no banco de dados...");
            long startTime = System.currentTimeMillis();
            MemeCategory savedCategory = categoryRepository.save(memeCategory);

            long endTime = System.currentTimeMillis();
            log.info("categoria criada com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return savedCategory;

        } catch (RuntimeException e) {
            log.error("Erro ao salvar categoria: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<MemeCategory> findById(Long id) {
        log.info("Buscando categoria com id: {}...", id);
        long startTime = System.currentTimeMillis();
        Optional<MemeCategory> retrievedMemeCategory = categoryRepository.findById(id);

        try {
            long endTime = System.currentTimeMillis();
            log.info("Categoria retornada com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return retrievedMemeCategory;
        } catch (RuntimeException e) {
            log.error("Erro ao buscar categoria: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<MemeCategory> findAll() {

        try {
            log.info("Buscando categorias de memes...");
            long startTime = System.currentTimeMillis();
            List<MemeCategory> categories = categoryRepository.findAll();

            long endTime = System.currentTimeMillis();
            log.info("Categorias retornadas com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return categories;
        } catch (RuntimeException e) {
            log.error("Erro ao buscar categorias: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public MemeCategory updateCategory(Long id, MemeCategory memeCategory) {
        log.info("Atualizando categoria com id: {}...", id);
        long startTime = System.currentTimeMillis();
        Optional<MemeCategory> retrievedMemeCategory = categoryRepository.findById(id);

        try {
            MemeCategory newMemeCategory = retrievedMemeCategory.get();

            if (memeCategory.getNome() != null) {
                newMemeCategory.setNome(memeCategory.getNome());
            }
            if (memeCategory.getDescricao() != null) {
                newMemeCategory.setDescricao(memeCategory.getDescricao());
            }
            MemeCategory savedCategory = categoryRepository.save(newMemeCategory);

            long endTime = System.currentTimeMillis();
            log.info("Categoria atualizada com sucesso. Tempo de processamento: {}ms", (endTime - startTime));
            return savedCategory;
        } catch (RuntimeException e) {
            if (retrievedMemeCategory.isEmpty()) {
                log.error("Erro ao buscar categoria: {}", e.getMessage(), e);
                throw new RuntimeException("Categoria inexistente");
            }
            log.error("Erro ao buscar categoria: {}", e.getMessage(), e);
            throw e;
        }
    }
}

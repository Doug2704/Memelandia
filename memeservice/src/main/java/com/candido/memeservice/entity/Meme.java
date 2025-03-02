package com.candido.memeservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Douglas Candido
 * entidade de Memes
 */
@Entity
@Getter
@Setter
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "url", nullable = false, length = 1000)
    private String url;

    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;

    @JoinColumn(name = "categoria_id", nullable = false)
    private Long idCategory;

    @JoinColumn(name = "usuario_id", nullable = false)
    private Long idUser;

    public Meme() {
    }

}
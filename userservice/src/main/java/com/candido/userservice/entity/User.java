package com.candido.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Douglas Candido
 * Entidade de usuários
 */
@Entity(name = "users")
@Getter
@Setter
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "nome", nullable = false)
        private String nome;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "data_cadastro", nullable = false)
        private Date dataCadastro;

        public User() {

    }
}

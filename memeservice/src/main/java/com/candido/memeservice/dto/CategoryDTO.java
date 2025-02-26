package com.candido.memeservice.dto;

import java.util.Date;

public record CategoryDTO(Long id, String nome, String descricao, Date dataCadastro) {
}

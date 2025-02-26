package com.candido.memeservice.dto;

import java.util.Date;

public record UserDTO(Long id, String nome, String email, Date dataCadastro) {
}

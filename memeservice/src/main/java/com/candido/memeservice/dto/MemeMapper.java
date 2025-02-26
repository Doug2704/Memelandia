package com.candido.memeservice.dto;

import com.candido.memeservice.entity.Meme;

public class MemeMapper {

    public static MemeDTO toDto(Meme meme, CategoryDTO categoryDTO, UserDTO user) {

        return new MemeDTO(meme.getId(), meme.getNome(), meme.getDescricao(), meme.getUrl(), categoryDTO.nome(), user.nome());
    }
}

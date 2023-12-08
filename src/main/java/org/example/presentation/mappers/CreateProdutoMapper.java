package org.example.presentation.mappers;

import org.example.data.Produto;
import org.example.presentation.DTO.CreateProdutoDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class CreateProdutoMapper {

    public static Produto toProduto(CreateProdutoDTO createProdutoDTO) {
        return new Produto(
            UUID.randomUUID(),
            createProdutoDTO.getNome(),
            createProdutoDTO.getDescricao(),
            createProdutoDTO.getEan13(),
            createProdutoDTO.getPreco(),
            createProdutoDTO.getQuantidade(),
            createProdutoDTO.getEstoque_min(),
                LocalDateTime.now(),
                null,
                false
        );
    }

}

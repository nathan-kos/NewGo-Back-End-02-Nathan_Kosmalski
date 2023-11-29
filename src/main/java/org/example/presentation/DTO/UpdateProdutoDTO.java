package org.example.presentation.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProdutoDTO {

    private String descricao;

    private Double preco;

    private Integer quantidade;

    private Integer estoque_min;

    public UpdateProdutoDTO() {
    }

    public UpdateProdutoDTO(String descricao, Double preco, Integer quantidade, Integer estoque_min, Boolean lativo) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
    }

}

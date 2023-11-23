package org.example.presentation.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProdutoDTO {

    private String nome;

    private String descricao;

    private String ean13;

    private Double preco;

    private Integer quantidade;

    private Integer estoque_min;

    public CreateProdutoDTO(String nome, String descricao, String ean13, Double preco, Integer quantidade, Integer estoque_min) {
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
    }

}

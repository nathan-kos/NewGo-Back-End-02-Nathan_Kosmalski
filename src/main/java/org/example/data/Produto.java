package org.example.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Produto {

    private Long id;

    private UUID hash;

    private String nome;

    private String descricao;

    private String ean13;

    private Double preco;

    private Integer quantidade;

    private Integer estoque_min;

    private LocalDateTime dtcreate;

    private LocalDateTime dtupdate;

    private Boolean lativo;
    
    public Produto() {
    }

    public Produto(UUID hash, String nome, String descricao, String ean13, Double preco, Integer quantidade, Integer estoque_min, LocalDateTime dtcreate, LocalDateTime dtupdate, Boolean lativo) {
        this.hash = hash;
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
        this.dtcreate = dtcreate;
        this.dtupdate = dtupdate;
        this.lativo = lativo;
    }

}

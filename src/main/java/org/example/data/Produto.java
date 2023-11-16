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

}

package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.InvalidParamsExcption;

import java.util.ArrayList;

public abstract class ListProdutoService {

    public static ArrayList<Produto> execute(int page, int limit) throws InvalidParamsExcption {

        if (page <= 0) {
            throw new InvalidParamsExcption("Página inválida!", 400);
        }

        if (limit <= 0) {
            throw new InvalidParamsExcption("limite inválido!", 400);
        }

        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        return produtoRepository.list(page, limit);

    }

}

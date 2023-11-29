package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.InvalidParamsException;

import java.util.ArrayList;

public abstract class ListProdutoService {

    public static ArrayList<Produto> execute(int page, int limit) throws InvalidParamsException {

        if (page <= 0) {
            throw new InvalidParamsException("Página inválida!", 400);
        }

        if (limit <= 0) {
            throw new InvalidParamsException("limite inválido!", 400);
        }

        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        return produtoRepository.list(page, limit);

    }

}

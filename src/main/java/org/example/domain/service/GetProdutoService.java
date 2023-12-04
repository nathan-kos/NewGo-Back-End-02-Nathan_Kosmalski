package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.UuidConverter;
import org.example.util.exception.InvalidParamsException;
import org.example.util.exception.NotFoundException;

import java.util.Optional;
import java.util.UUID;

public abstract class GetProdutoService {

    public static Produto execute(String parametro) throws NotFoundException, InvalidParamsException {

        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        if(!UuidConverter.isValid(parametro)){
            throw new InvalidParamsException("Parametro inválido", 400);
        }

        Optional<Produto> produto = produtoRepository.findByHash(UuidConverter.toUuid(parametro));


        if (!produto.isPresent()) {
            throw new NotFoundException("Produto não encontrado", 404);
        }

        return produto.get();

    }
}

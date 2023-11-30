package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.NotFoundException;

import java.util.Optional;
import java.util.UUID;

public abstract class GetProdutoService {

    public static Produto execute(String parametro) throws NotFoundException {

        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        Optional<Produto> produto = Optional.empty();

        try {
            produto = produtoRepository.findByHash(UUID.fromString(parametro));

            if (produto.isPresent()) {
                return produto.get();
            }
        }catch (IllegalArgumentException e) {
            // do nothing
        }

        produto = produtoRepository.findByEan13(parametro);

        if (produto.isPresent()) {
            return produto.get();
        }

        produto = produtoRepository.findByNome(parametro.toUpperCase());

        if (produto.isPresent()) {
            return produto.get();
        }

        try {
            produto = produtoRepository.findById(Long.valueOf(parametro));
        }catch (NumberFormatException e) {
            // do nothing
        }
        if (produto.isPresent()) {
            return produto.get();
        }

        throw new NotFoundException("Produto não encontrado", 404);
    }
}

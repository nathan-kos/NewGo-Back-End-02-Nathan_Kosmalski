package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.DeactivatedProductException;
import org.example.util.exception.InternalServerErrorException;
import org.example.util.exception.NotFoundException;

import java.util.Optional;
import java.util.UUID;

public class DeleteProdutoService {

    public static void execute(String hash) throws NotFoundException, DeactivatedProductException, InternalServerErrorException {


        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        UUID uuid = null;

        try {
           uuid = UUID.fromString(hash);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Produto não encontrado!", 404);
        }

        Optional<Produto> produto = produtoRepository.findByHash(uuid);

        if(!produto.isPresent()) {
            throw new NotFoundException("Produto não encontrado!", 404);
        }

        if(!produto.get().getLativo()) {
            throw new DeactivatedProductException("Produto desativado!", 400);
        }

        produto.get().setLativo(false);

        try {
            produtoRepository.setLativoProduto(produto.get());
        }catch (Exception e) {
            throw new InternalServerErrorException("Erro ao desativar produto!", 500);
        }

    }

}

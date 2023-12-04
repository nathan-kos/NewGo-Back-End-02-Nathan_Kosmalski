package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.UuidConverter;
import org.example.util.exception.DeactivatedProductException;
import org.example.util.exception.InternalServerErrorException;
import org.example.util.exception.NotFoundException;

import java.util.Optional;
import java.util.UUID;

public abstract class DeleteProdutoService {

    public static void execute(String hash) throws NotFoundException, DeactivatedProductException, InternalServerErrorException {


        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        if(hash == null || hash.isEmpty() || !UuidConverter.isValid(hash)){
            throw new NotFoundException("Produto não encontrado!", 404);
        }

        UUID uuid = UuidConverter.toUuid(hash) ;

        Optional<Produto> produto = produtoRepository.findByHash(uuid);

        if(!produto.isPresent()) {
            throw new NotFoundException("Produto não encontrado!", 404);
        }

        try {
            produtoRepository.delete(produto.get());
        }catch (Exception e) {
            throw new InternalServerErrorException("Erro ao deletar produto!", 500);
        }

    }

}

package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.presentation.DTO.UpdateLativoDTO;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.InternalServerErrorException;
import org.example.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public abstract class UpdateLativoService {

    public static Produto execute(UUID hash, UpdateLativoDTO lativo) throws NotFoundException, InternalServerErrorException {

        ProdutoRepository repository = LocalizadorDeServico.getProdutoRepository();

        Optional<Produto> produto = repository.findByHash(hash);

        if (!produto.isPresent()) {
            throw new NotFoundException("Produto não encontrado", 404);
        }

        Produto toUpdate = produto.get();

        toUpdate.setLativo(lativo.getLativo());
        toUpdate.setDtupdate(LocalDateTime.now());

        try {
            repository.setLativoProduto(toUpdate);
            return toUpdate;
        }catch (Exception e){
            throw new InternalServerErrorException("Erro ao atualizar produto", 500);
        }

    }
}

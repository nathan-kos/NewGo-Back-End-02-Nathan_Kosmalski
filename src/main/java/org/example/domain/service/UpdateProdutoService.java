package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.presentation.DTO.UpdateProdutoDTO;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.InternalServerErrorException;
import org.example.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateProdutoService {

    public static Produto execute(Produto produto) throws NotFoundException, InternalServerErrorException {

        ProdutoRepository produtoRepository = LocalizadorDeServico.getProdutoRepository();

        Optional<Produto> existente = produtoRepository.findByHash(produto.getHash());

        if (!existente.isPresent()) {
            throw new NotFoundException("Produto não encontrado", 404);
        }

        produto = setProduto(produto, existente.get());

        try {
            return produtoRepository.update(produto);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException("Erro ao atualizar produto", 500);
        }

    }

    private static Produto setProduto(Produto produto, Produto existente){
        if(produto.getDescricao() == null || produto.getDescricao().isEmpty()){
            produto.setDescricao(existente.getDescricao());
        }

        if(produto.getPreco() == null || produto.getPreco() < 0){
            produto.setPreco(existente.getPreco());
        }

        if(produto.getQuantidade() == null || produto.getQuantidade() < 0){
            produto.setQuantidade(existente.getQuantidade());
        }

        if(produto.getEstoque_min() == null || produto.getEstoque_min() < 0){
            produto.setEstoque_min(existente.getEstoque_min());
        }

        if(produto.getLativo() == null){
            produto.setLativo(existente.getLativo());
        }

        produto.setNome(existente.getNome());
        produto.setEan13(existente.getEan13());

        produto.setDtcreate(existente.getDtcreate());
        produto.setDtupdate(LocalDateTime.now());

        return produto;
    }
}

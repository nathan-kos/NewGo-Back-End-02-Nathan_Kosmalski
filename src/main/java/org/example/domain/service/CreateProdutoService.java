package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.ExistingProductException;
import org.example.util.exception.InternalServerErrorException;
import org.example.util.exception.InvalidProductException;

import java.util.Optional;

public abstract class CreateProdutoService {

    public static Produto execute(Produto produto) throws InternalServerErrorException, ExistingProductException, InvalidProductException {

       ProdutoRepository repository = LocalizadorDeServico.getProdutoRepository();

       if(produto.getNome() == null || produto.getNome().isEmpty()){
           throw new InvalidProductException("Nome do produto inválido", 400);
       }

       if(produto.getEstoque_min() == null || produto.getEstoque_min() < 0){
           produto.setEstoque_min(0);
       }

       if(produto.getQuantidade() == null || produto.getQuantidade() < 0){
           produto.setQuantidade(0);
       }

       if(produto.getPreco() == null || produto.getPreco() < 0){
           produto.setPreco(0.0);
       }

       if(produto.getEan13() == null || produto.getEan13().length() != 13) {
           throw new InvalidProductException("Ean13 do produto inválido", 400);
       }

        Optional<Produto> produtoExitente = repository.findByEan13(produto.getEan13());

        if(produtoExitente.isPresent()){
            throw new ExistingProductException("Produto já cadastrado", 400);
        }

        produto.setNome(produto.getNome().toUpperCase());

        produtoExitente = repository.findByNome(produto.getNome());

        if(produtoExitente.isPresent()){
            throw new ExistingProductException("Produto já cadastrado", 400);
        }

       try {
           return repository.save(produto);
       }catch (Exception e){
           System.out.println(e.getMessage());
           throw new InternalServerErrorException("Erro ao salvar produto", 500);
       }

    }

}

package org.example.presentation.mappers;

import org.example.data.Produto;
import org.example.presentation.DTO.UpdateProdutoDTO;

import java.util.UUID;

public class UpdateProdutoMapper {

   public static Produto toProduto(UpdateProdutoDTO updateProdutoDTO, UUID hash) {
       Produto produto = new Produto();

            produto.setDescricao(updateProdutoDTO.getDescricao());
            produto.setPreco(updateProdutoDTO.getPreco());
            produto.setQuantidade(updateProdutoDTO.getQuantidade());
            produto.setEstoque_min(updateProdutoDTO.getEstoque_min());
            produto.setLativo(updateProdutoDTO.getLativo());
            produto.setHash(hash);

         return produto;
   }
}

package org.example.domain.service;

import org.example.data.Produto;
import org.example.data.repository.ProdutoRepository;
import org.example.presentation.DTO.UpdateLativoDTO;
import org.example.util.UuidConverter;
import org.example.util.exception.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.repository = produtoRepository;
    }

    public Produto createProduto(Produto produto) throws InternalServerErrorException, ExistingProductException, InvalidProductException, InvalidParamsException {

        if(produto.getNome() == null || produto.getNome().isEmpty()){
            throw new InvalidProductException("Nome do produto inválido", 400);
        }

        if(produto.getEstoque_min() == null){
            produto.setEstoque_min(0);
        }

        if(produto.getEstoque_min() < 0){
            throw new InvalidParamsException("Estoque minimo inválido", 400);
        }

        if(produto.getQuantidade() == null){
            produto.setQuantidade(0);
        }

        if(produto.getQuantidade() < 0){
            throw new InvalidParamsException("Quantidade inválida", 400);
        }

        if(produto.getPreco() == null){
            produto.setPreco(0.0);
        }

        if(produto.getPreco() < 0){
            throw new InvalidParamsException("Preço inválido", 400);
        }

        if(produto.getEan13() == null) {
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

    public void deleteProduto(String hash) throws NotFoundException, DeactivatedProductException, InternalServerErrorException {


        if(hash == null || hash.isEmpty() || !UuidConverter.isValid(hash)){
            throw new NotFoundException("Produto não encontrado!", 404);
        }

        UUID uuid = UuidConverter.toUuid(hash) ;

        Optional<Produto> produto = repository.findByHash(uuid);

        if(!produto.isPresent()) {
            throw new NotFoundException("Produto não encontrado!", 404);
        }

        try {
            repository.delete(produto.get());
        }catch (Exception e) {
            throw new InternalServerErrorException("Erro ao deletar produto!", 500);
        }

    }

    public Produto getProduto(String parametro) throws NotFoundException, InvalidParamsException {

        if(!UuidConverter.isValid(parametro)){
            throw new InvalidParamsException("Parametro inválido", 400);
        }

        Optional<Produto> produto = repository.findByHash(UuidConverter.toUuid(parametro));


        if (!produto.isPresent()) {
            throw new NotFoundException("Produto não encontrado", 404);
        }

        return produto.get();

    }

    public ArrayList<Produto> listProduto(int page, int limit) throws InvalidParamsException {

        if (page <= 0) {
            throw new InvalidParamsException("Página inválida!", 400);
        }

        if (limit <= 0) {
            throw new InvalidParamsException("limite inválido!", 400);
        }

        return repository.list(page, limit);

    }

    public Produto updateLativo(UUID hash, UpdateLativoDTO lativo) throws NotFoundException, InternalServerErrorException {

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

    public Produto updateProduto(Produto produto) throws NotFoundException, InternalServerErrorException, DeactivatedProductException, InvalidParamsException {

        Optional<Produto> existente = repository.findByHash(produto.getHash());

        if(produto.getDescricao() == null || produto.getPreco() == null || produto.getQuantidade() == null || produto.getEstoque_min() == null){
            throw new InvalidParamsException("Parametros inválidos", 400);
        }

        if(produto.getEstoque_min() < 0 || produto.getQuantidade() < 0 || produto.getPreco() < 0){
            throw new InvalidParamsException("Parametros inválidos", 400);
        }

        if (!existente.isPresent()) {
            throw new NotFoundException("Produto não encontrado", 404);
        }

        if (!existente.get().getLativo()){
            throw new DeactivatedProductException("Produto desativado", 400);
        }

        produto = setProduto(produto, existente.get());

        if (produto.getEstoque_min().equals(existente.get().getEstoque_min()) && produto.getQuantidade().equals(existente.get().getQuantidade()) && produto.getPreco().equals(existente.get().getPreco()) && produto.getDescricao().equals(existente.get().getDescricao())) {
            return produto;
        }

        try {
            produto.setDtupdate(LocalDateTime.now());
            return repository.update(produto);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException("Erro ao atualizar produto", 500);
        }

    }

    private static Produto setProduto(Produto produto, Produto existente){

        produto.setNome(existente.getNome());
        produto.setEan13(existente.getEan13());

        produto.setDtcreate(existente.getDtcreate());
        produto.setDtupdate(existente.getDtupdate());
        produto.setLativo(existente.getLativo());

        return produto;
    }

}

package org.example.data.repository;

import org.example.data.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository {

    public Produto save(Produto produto) throws SQLException;

    public Produto update(Produto produto) throws SQLException;

    public Optional<Produto> findById(Long id);

    public Optional<Produto> findByHash(UUID hash);

    public Optional<Produto> findByEan13(String ean13);

    public Optional<Produto> findByNome(String nome);

    public ArrayList<Produto> list(int page, int limit);

    public ArrayList<Produto> listByLativo(Boolean lativo);

    public void setLativoProduto(Produto produto) throws SQLException;

    public void delete(Produto produto) throws SQLException;
}

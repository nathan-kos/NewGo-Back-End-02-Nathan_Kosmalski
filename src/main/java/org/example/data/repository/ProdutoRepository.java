package org.example.data.repository;

import org.example.data.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public interface ProdutoRepository {

    public Produto save(Produto produto) throws SQLException;

    public Produto update(Produto produto) throws SQLException;

    public Produto findById(Long id);

    public Produto findByHash(UUID hash);

    public Produto findByEan13(String ean13);

    public Produto findByNome(String nome);

    public ArrayList<Produto> list();

    public ArrayList<Produto> listByLativo(Boolean lativo);

}

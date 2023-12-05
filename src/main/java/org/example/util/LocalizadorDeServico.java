package org.example.util;

import org.example.data.repository.ProdutoRepository;
import org.example.data.repository.ProdutoRepositoryPostgresql;
import org.example.domain.service.ProdutoService;

import java.sql.Connection;

public class LocalizadorDeServico {


    public static Connection getConnection() {
        try {
            return ConnectionFactory.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ProdutoRepository getProdutoRepository() {
        return new ProdutoRepositoryPostgresql(getConnection());
    }

    public static ProdutoService getProdutoService() {
        return new ProdutoService(getProdutoRepository());
    }

}

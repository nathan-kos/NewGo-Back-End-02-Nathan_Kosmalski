package org.example.data.repository;

import org.example.data.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class ProdutoRepositoryPostgresql implements ProdutoRepository {

    private final Connection conexao;

    public ProdutoRepositoryPostgresql(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public Produto save(Produto produto) throws SQLException {
        try {
            conexao.setAutoCommit(false);
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO produto (hash, nome, descricao, ean13, preco, quantidade, estoque_min, lativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setObject(1, produto.getHash());
            statement.setString(2, produto.getNome());
            statement.setString(3, produto.getDescricao());
            statement.setString(4, produto.getEan13());
            statement.setDouble(5, produto.getPreco());
            statement.setInt(6, produto.getQuantidade());
            statement.setInt(7, produto.getEstoque_min());
            statement.setBoolean(8, produto.getLativo());
            statement.execute();
            conexao.commit();
            return produto;
        }catch (Exception error) {
            conexao.rollback();
            throw new RuntimeException("Erro ao salvar produto");
        }
    }

    @Override
    public Produto update(Produto produto) throws SQLException {
        try {
            conexao.setAutoCommit(false);
            PreparedStatement statement = conexao.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, quantidade = ?, estoque_min = ?, lativo = ? WHERE hash = ?");
            statement.setString(1, produto.getDescricao());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setInt(4, produto.getEstoque_min());
            statement.setBoolean(5, produto.getLativo());
            statement.setObject(6, produto.getHash());
            statement.execute();
            conexao.commit();
            return produto;
        }catch (Exception error) {
            conexao.rollback();
            throw new RuntimeException("Erro ao atualizar produto");
        }
    }

    @Override
    public Optional<Produto> findById(Long id) {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produto WHERE id = ?");
            statement.setLong(1, id);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                produto.setLativo(resultado.getBoolean("lativo"));
                return Optional.of(produto);
            }

            return Optional.empty();
        }catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produto");
        }
    }

    @Override
    public Optional<Produto> findByHash(UUID hash) {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produto WHERE hash = ?");
            statement.setObject(1, hash);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                produto.setLativo(resultado.getBoolean("lativo"));
                return Optional.of(produto);
            }
            return Optional.empty();
        }catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produto");
        }
    }

    @Override
    public Optional<Produto> findByEan13(String ean13) {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produto WHERE ean13 = ?");
            statement.setString(1, ean13);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                produto.setLativo(resultado.getBoolean("lativo"));
                return Optional.of(produto);
            }
            return Optional.empty();
        }catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produto");
        }
    }

    @Override
    public Optional<Produto> findByNome(String nome) {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produto WHERE nome = ?");
            statement.setString(1, nome);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                produto.setLativo(resultado.getBoolean("lativo"));
                return Optional.of(produto);
            }
            return Optional.empty();
        }catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produto");
        }
    }

    @Override
    public ArrayList<Produto> list() {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produto");
            statement.execute();
            ArrayList<Produto> produtos = new ArrayList<>();
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                produto.setLativo(resultado.getBoolean("lativo"));
                produtos.add(produto);
            }
            return produtos;
        }catch (Exception error) {
            throw new RuntimeException("Erro ao listar produtos");
        }
    }

    @Override
    public ArrayList<Produto> listByLativo(Boolean lativo) {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produto WHERE lativo = ?");
            statement.setBoolean(1, lativo);
            statement.execute();
            ArrayList<Produto> produtos = new ArrayList<>();
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                produto.setLativo(resultado.getBoolean("lativo"));
                produtos.add(produto);
            }
            return produtos;
        }catch (Exception error) {
            throw new RuntimeException("Erro ao listar produtos");
        }
    }
}

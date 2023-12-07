package org.example.data.repository;

import org.example.data.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO produtos (hash, nome, descricao, ean13, preco, quantidade, estoque_min, lativo, dtcreate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setObject(1, produto.getHash());
            statement.setString(2, produto.getNome());
            statement.setString(3, produto.getDescricao());
            statement.setString(4, produto.getEan13());
            statement.setDouble(5, produto.getPreco());
            statement.setInt(6, produto.getQuantidade());
            statement.setInt(7, produto.getEstoque_min());
            statement.setBoolean(8, produto.getLativo());
            statement.setObject(9, produto.getDtcreate());
            statement.execute();
            conexao.commit();
            return produto;
        }catch (Exception error) {
            System.out.println(error.getMessage());
            conexao.rollback();
            throw new RuntimeException("Erro ao salvar produto");
        }
    }

    @Override
    public Produto update(Produto produto) throws SQLException {
        try {
            conexao.setAutoCommit(false);
            PreparedStatement statement = conexao.prepareStatement("UPDATE produtos SET descricao = ?, preco = ?, quantidade = ?, estoque_min = ?, dtupdate = ? WHERE hash = ?");
            statement.setString(1, produto.getDescricao());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setInt(4, produto.getEstoque_min());
            statement.setObject(5, LocalDateTime.now());
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
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            statement.setLong(1, id);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                if(resultado.getString("descricao") != null){
                    produto.setDescricao(resultado.getString("descricao"));
                }
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                if (resultado.getTimestamp("dtupdate") != null) {
                    produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                }
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
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produtos WHERE hash = ?");
            statement.setObject(1, hash);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                if(resultado.getString("descricao") != null){
                    produto.setDescricao(resultado.getString("descricao"));
                }
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                if (resultado.getTimestamp("dtupdate") != null) {
                    produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                }
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
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produtos WHERE ean13 = ?");
            statement.setString(1, ean13);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                if(resultado.getString("descricao") != null){
                    produto.setDescricao(resultado.getString("descricao"));
                }
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                if (resultado.getTimestamp("dtupdate") != null) {
                    produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                }
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
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produtos WHERE nome = ?");
            statement.setString(1, nome);
            statement.execute();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                if(resultado.getString("descricao") != null){
                    produto.setDescricao(resultado.getString("descricao"));
                }
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                if (resultado.getTimestamp("dtupdate") != null) {
                    produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                }
                produto.setLativo(resultado.getBoolean("lativo"));
                return Optional.of(produto);
            }
            return Optional.empty();
        }catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produto");
        }
    }

    @Override
    public ArrayList<Produto> list(int page, int limit) {
        try {
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produtos LIMIT ? OFFSET ?");
            statement.setInt(1, limit);
            statement.setInt(2, (page - 1) * limit);
            statement.execute();
            ArrayList<Produto> produtos = new ArrayList<>();
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                if(resultado.getString("descricao") != null){
                    produto.setDescricao(resultado.getString("descricao"));
                }
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                if (resultado.getTimestamp("dtupdate") != null) {
                    produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                }
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
            PreparedStatement statement = conexao.prepareStatement("SELECT * FROM produtos WHERE lativo = ?");
            statement.setBoolean(1, lativo);
            statement.execute();
            ArrayList<Produto> produtos = new ArrayList<>();
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setHash((UUID) resultado.getObject("hash"));
                produto.setNome(resultado.getString("nome"));
                if(resultado.getString("descricao") != null){
                    produto.setDescricao(resultado.getString("descricao"));
                }
                produto.setEan13(resultado.getString("ean13"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setEstoque_min(resultado.getInt("estoque_min"));
                produto.setDtcreate(resultado.getTimestamp("dtcreate").toLocalDateTime());
                if (resultado.getTimestamp("dtupdate") != null) {
                    produto.setDtupdate(resultado.getTimestamp("dtupdate").toLocalDateTime());
                }
                produto.setLativo(resultado.getBoolean("lativo"));
                produtos.add(produto);
            }
            return produtos;
        }catch (Exception error) {
            throw new RuntimeException("Erro ao listar produtos");
        }
    }

    @Override
    public void setLativoProduto(Produto produto) throws SQLException {
        try {
            conexao.setAutoCommit(false);
            PreparedStatement statement = conexao.prepareStatement("UPDATE produtos SET lativo = ? WHERE hash = ?");
            statement.setBoolean(1, produto.getLativo());
            statement.setObject(2, produto.getHash());
            statement.execute();
            conexao.commit();
        }catch (Exception error) {
            conexao.rollback();
            throw new RuntimeException("Erro ao desativar produto");
        }
    }

    @Override
    public void delete(Produto produto) throws SQLException {
        try {
            conexao.setAutoCommit(false);
            PreparedStatement statement = conexao.prepareStatement("DELETE FROM produtos WHERE hash = ?");
            statement.setObject(1, produto.getHash());
            statement.execute();
            conexao.commit();
        }catch (Exception error) {
            conexao.rollback();
            throw new RuntimeException("Erro ao deletar produto");
        }
    }
}

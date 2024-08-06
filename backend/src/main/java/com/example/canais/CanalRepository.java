package com.example.canais;

import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CanalRepository {

    private final DataSource dataSource;

    public CanalRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Canal> listar() {
        List<Canal> canais = new ArrayList<>();
        String sql = "SELECT * FROM canais";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Canal canal = new Canal();
                canal.setId(resultSet.getInt("id"));
                canal.setNome(resultSet.getString("nome"));
                canal.setDescricao(resultSet.getString("descricao"));
                canal.setQuantidadeInscritos(resultSet.getInt("quantidade_inscritos"));
                canais.add(canal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canais;
    }

    public Canal buscarPorId(int id) {
        Canal canal = null;
        String sql = "SELECT * FROM canais WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    canal = new Canal();
                    canal.setId(resultSet.getInt("id"));
                    canal.setNome(resultSet.getString("nome"));
                    canal.setDescricao(resultSet.getString("descricao"));
                    canal.setQuantidadeInscritos(resultSet.getInt("quantidade_inscritos"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canal;
    }

    public void adicionar(Canal canal) {
        String sql = "INSERT INTO canais (nome, descricao, quantidade_inscritos) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, canal.getNome());
            statement.setString(2, canal.getDescricao());
            statement.setInt(3, canal.getQuantidadeInscritos());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Canal canal) {
        String sql = "UPDATE canais SET nome = ?, descricao = ?, quantidade_inscritos = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, canal.getNome());
            statement.setString(2, canal.getDescricao());
            statement.setInt(3, canal.getQuantidadeInscritos());
            statement.setInt(4, canal.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM canais WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Canal> filtrar(String nome, String descricao, Integer quantidadeInscritos) {
        List<Canal> canais = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM canais WHERE 1=1");

        if (!nome.isEmpty()) {
            sql.append(" AND nome LIKE ?");
        }
        if (!descricao.isEmpty()) {
            sql.append(" AND descricao LIKE ?");
        }
        if (quantidadeInscritos != null) {
            sql.append(" AND quantidade_inscritos = ?");
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            if (!nome.isEmpty()) {
                statement.setString(paramIndex++, "%" + nome + "%");
            }
            if (!descricao.isEmpty()) {
                statement.setString(paramIndex++, "%" + descricao + "%");
            }
            if (quantidadeInscritos != null) {
                statement.setInt(paramIndex, quantidadeInscritos);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Canal canal = new Canal();
                    canal.setId(resultSet.getInt("id"));
                    canal.setNome(resultSet.getString("nome"));
                    canal.setDescricao(resultSet.getString("descricao"));
                    canal.setQuantidadeInscritos(resultSet.getInt("quantidade_inscritos"));
                    canais.add(canal);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canais;
    }
}

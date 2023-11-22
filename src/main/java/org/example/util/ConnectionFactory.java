package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    // singleton pattern para conexão com o banco de dados
    private static Connection connection;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            Properties properties = PropertiesReader.lerProperties();
            connection = DriverManager.getConnection(
                    properties.getProperty("banco.url"),
                    properties.getProperty("banco.usuario") ,
                    properties.getProperty("banco.senha")
            );
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null || !connection.isClosed()) {
            connection.close();
        }
    }

}

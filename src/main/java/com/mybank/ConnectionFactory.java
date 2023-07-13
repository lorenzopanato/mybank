package com.mybank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    String url = "jdbc:mysql://localhost:3306/mybank";
    String user = "root";
    String password = "@root123";

    //conexao com o banco
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

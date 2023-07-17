package com.mybank.transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    private Connection conn;

    public TransactionDAO(Connection connection) {
        this.conn = connection;
    }

    public void create(Transaction transaction) {
        PreparedStatement ps;

        String sql = "INSERT INTO historico_transaçoes (id, numero_conta, tipo, data, valor) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, transaction.getId());
            ps.setInt(2, transaction.getAccountNumber());
            ps.setString(3, transaction.getType());
            ps.setObject(4, transaction.getDate());
            ps.setBigDecimal(5, transaction.getAmount());

            ps.execute();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

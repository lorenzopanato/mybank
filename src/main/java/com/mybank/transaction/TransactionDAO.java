package com.mybank.transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    public Set<Transaction> list() {

        PreparedStatement ps;
        ResultSet rs;

        Set<Transaction> transactions = new HashSet<>();

        String sql = "SELECT * FROM historico_transaçoes";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Integer id = rs.getInt(1);
                Integer numberAccount = rs.getInt(2);
                String type = rs.getString(3);
                LocalDate date = rs.getDate(4).toLocalDate();
                BigDecimal amount = rs.getBigDecimal(5);

                transactions.add(new Transaction(id, numberAccount, type, date, amount));
            }
            ps.close();
            conn.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return transactions;
    }
}

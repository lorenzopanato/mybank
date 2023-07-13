package com.mybank.account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//interacoes com o banco
public class AccountDAO {

    private Connection conn;

    public AccountDAO(Connection connection) {
        this.conn = connection;
    }

    public void create(Integer accountNumber, String customerName, String customerCpf,
                       String customerEmail) {

        PreparedStatement ps;

        String sql = "INSERT INTO conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, accountNumber);
            ps.setBigDecimal(2, BigDecimal.ZERO);
            ps.setString(3, customerName);
            ps.setString(4, customerCpf);
            ps.setString(5, customerEmail);

            ps.execute();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

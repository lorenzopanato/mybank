package com.mybank.account;

import com.mybank.customer.Customer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

//interacoes com o banco
public class AccountDAO {

    private Connection conn;

    public AccountDAO(Connection connection) {
        this.conn = connection;
    }

    public void create(Integer accountNumber, String customerName, String customerCpf,
                       String customerEmail, String typeAccount) {
        PreparedStatement ps;

        String sql = "INSERT INTO conta (numero_conta, saldo, cliente_nome, cliente_cpf, cliente_email, tipo_conta) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, accountNumber);
            ps.setBigDecimal(2, BigDecimal.ZERO);
            ps.setString(3, customerName);
            ps.setString(4, customerCpf);
            ps.setString(5, customerEmail);
            ps.setString(6, typeAccount);

            ps.execute();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Set<Account> list() {

        PreparedStatement ps;
        ResultSet rs;

        Set<Account> accounts = new HashSet<>();

        String sql = "SELECT * FROM conta";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Integer number = rs.getInt(1);
                BigDecimal balance = rs.getBigDecimal(2);
                String name = rs.getString(3);
                String cpf = rs.getString(4);
                String email = rs.getString(5);
                String typeAccount = rs.getString(6);

                Customer customer = new Customer(name, cpf, email);

                if(typeAccount.equals("Poupança"))
                    accounts.add(new savingsAccount(number, balance, customer));
                else
                    accounts.add(new checkingAccount(number, balance, customer));
            }
            ps.close();
            conn.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return accounts;
    }

    public void update(Integer accountNumber, BigDecimal amount) {

        PreparedStatement ps;

        String sql = "UPDATE conta SET saldo = ? WHERE numero_conta = ?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setBigDecimal(1, amount);
            ps.setInt(2, accountNumber);

            ps.execute();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Integer number) {

        PreparedStatement ps;

        String sql = "DELETE FROM conta WHERE numero_conta = ?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, number);

            ps.execute();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

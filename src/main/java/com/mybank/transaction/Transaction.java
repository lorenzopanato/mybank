package com.mybank.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Integer id;
    private Integer accountNumber;
    private String type;
    private LocalDate date;
    private BigDecimal amount;

    public Transaction(Integer id, Integer accountNumber, String type, LocalDate date, BigDecimal amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transação{" +
                "id=" + id +
                ", numero da conta=" + accountNumber +
                ", tipo='" + type + '\'' +
                ", data=" + date +
                ", valor=" + amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

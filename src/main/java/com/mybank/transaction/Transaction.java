package com.mybank.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Transaction {

    private Integer id;
    private Integer accountNumber;
    private String type;
    private LocalDate date;
    private BigDecimal amount;

    public Transaction(Integer accountNumber, String type, BigDecimal amount) {
        this.id = generateId();
        this.accountNumber = accountNumber;
        this.type = type;
        this.date = LocalDate.now();
        this.amount = amount;
    }

    private Integer generateId() {
        Random random = new Random();

        //numero inteiro de quatro digitos
        return random.nextInt(9000) + 1000;
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

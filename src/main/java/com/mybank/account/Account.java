package com.mybank.account;

import com.mybank.customer.Customer;

import java.math.BigDecimal;

public abstract class Account {

    private Integer number;
    private BigDecimal balance;
    private Customer customer;

    public Account(Integer number, BigDecimal balance, Customer customer) {
        if(number <= 0)
            throw new IllegalArgumentException("O número da conta deve ser maior que zero.");
        this.number = number;

        this.balance = balance;

        this.customer = customer;
    }

    @Override
    public String toString() {
        return "{" +
                "numero='" + number + '\'' +
                ", saldo=" + balance +
                ", titular=" + customer +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }
}

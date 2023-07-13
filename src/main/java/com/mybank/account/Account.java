package com.mybank.account;

import com.mybank.customer.Customer;

import java.math.BigDecimal;

public class Account {

    private Integer number;
    private BigDecimal balance;
    private Customer customer;

    public Account(Integer number, BigDecimal balance, Customer customer) {
        if(number <= 0)
            throw new IllegalArgumentException("O número da conta deve ser maior que zero.");
        this.number = number;

        //zero
        this.balance = balance;

        this.customer = customer;
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

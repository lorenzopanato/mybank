package com.mybank.account;

import com.mybank.customer.Customer;

import java.math.BigDecimal;

public class savingsAccount extends Account {

    public savingsAccount(Integer number, BigDecimal balance, Customer customer) {
        super(number, balance, customer);
    }

    @Override
    public String toString() {
        return "Conta poupança" + super.toString();
    }
}

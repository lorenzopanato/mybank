package com.mybank.account;

import com.mybank.customer.Customer;

import java.math.BigDecimal;

public class checkingAccount extends Account {

    public checkingAccount(Integer number, BigDecimal balance, Customer customer) {
        super(number, balance, customer);
    }

    @Override
    public String toString() {
        return "Conta corrente" + super.toString();
    }
}

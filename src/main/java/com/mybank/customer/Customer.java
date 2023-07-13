package com.mybank.customer;

public class Customer {

    private String name;
    private String cpf;
    private String email;

    public Customer(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;

        if(!email.contains("@"))
            throw new IllegalArgumentException("O email deve possuir um @");

        this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}

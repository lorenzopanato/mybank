package com.mybank.app;

import com.mybank.ConnectionFactory;
import com.mybank.account.AccountDAO;


import java.sql.Connection;
import java.util.Scanner;

public class BankApplication {

    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

        menu();
        int option = sc.nextInt();

        switch (option) {
            case 1:
                createAccount();
                break;
        }
    }

    public static void menu() {
        System.out.println("1 - Criar uma conta");
        System.out.println("2 - Realizar depósito em uma conta");
        System.out.println("3 - Realizar saque em uma conta");
        System.out.println("4 - Realizar transferência em uma conta");
        System.out.println("5 - Listar contas abertas");
        System.out.println("6 - Excluir conta");
    }

    public static void createAccount() {
        System.out.println("Informe o número da conta");
        int number = sc.nextInt();

        System.out.println("Informe o nome do cliente");
        String name = sc.next();

        System.out.println("Informe o cpf do cliente");
        String cpf = sc.next();

        System.out.println("Informe o email do cliente");
        String email = sc.next();

        Connection conn = new ConnectionFactory().getConnection();

        new AccountDAO(conn).create(number, name, cpf, email);

        System.out.println("Conta criada com sucesso!");
    }
}
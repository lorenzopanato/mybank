package com.mybank.app;

import com.mybank.ConnectionFactory;
import com.mybank.account.Account;
import com.mybank.account.AccountDAO;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class BankApplication {

    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

        int option = 0;

        do {
            menu();
            try {
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositAmount();
                        break;
                    case 3:
                        withdrawValue();
                        break;
                    case 4:
                        transferAmount();
                        break;
                    case 5:
                        listAccounts();
                        break;
                    case 6:
                        deleteAccount();
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Valor inválido. Tente novamente");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Digite um número inteiro");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while(option != 7);

        sc.close();
    }

    private static void menu() {
        System.out.println("1 - Criar uma conta");
        System.out.println("2 - Realizar depósito em uma conta");
        System.out.println("3 - Realizar saque em uma conta");
        System.out.println("4 - Realizar transferência");
        System.out.println("5 - Listar contas cadastradas");
        System.out.println("6 - Excluir conta");
        System.out.println("7 - Sair");
    }

    private static void createAccount() {
        System.out.println("Informe o número da conta:");
        int number = sc.nextInt();

        System.out.println("Informe o nome do cliente:");
        String name = sc.next();

        System.out.println("Informe o cpf do cliente:");
        String cpf = sc.next();

        System.out.println("Informe o email do cliente:");
        String email = sc.next();

        int typeAccountNumber;
        String typeAccount = null;
        do {
            System.out.println("Informe o tipo da conta (1-Corrente; 2-Poupança):");
            typeAccountNumber = sc.nextInt();

            if (typeAccountNumber == 1)
                typeAccount = "Corrente";
            else if (typeAccountNumber == 2)
                typeAccount = "Poupança";
            else
                System.out.println("Tipo inválido. Tente novamente");
        } while(typeAccountNumber != 1 && typeAccountNumber != 2);

        Connection conn = new ConnectionFactory().getConnection();

        new AccountDAO(conn).create(number, name, cpf, email, typeAccount);

        System.out.println("Conta criada com sucesso!");
    }

    private static void listAccounts() {
        System.out.println("Contas cadastradas:");

        Connection conn = new ConnectionFactory().getConnection();

        Set<Account> accounts = new AccountDAO(conn).list();

        accounts.forEach(account -> System.out.println(account));
    }

    private static void depositAmount() {
        System.out.println("Informe o número da conta em que deseja realizar o depósito:");
        Integer number = sc.nextInt();

        System.out.println("Informe o valor:");
        BigDecimal amount = sc.nextBigDecimal();

        Account account = searchAccountByNumber(number);

        BigDecimal newBalance = account.getBalance().add(amount);

        Connection conn = new ConnectionFactory().getConnection();

        new AccountDAO(conn).update(number, newBalance);

        System.out.println("Depósito realizado com sucesso!");
    }

    private static void withdrawValue() {
        System.out.println("Informe o número da conta em que deseja realizar o saque:");
        Integer number = sc.nextInt();

        System.out.println("Informe o valor:");
        BigDecimal amount = sc.nextBigDecimal();

        Account account = searchAccountByNumber(number);

        BigDecimal newBalance = account.getBalance().subtract(amount);

        Connection conn = new ConnectionFactory().getConnection();

        new AccountDAO(conn).update(number, newBalance);

        System.out.println("Saque realizado com sucesso!");
    }

    private static Account searchAccountByNumber(Integer number) {
        Connection conn = new ConnectionFactory().getConnection();

        Set<Account> accounts = new AccountDAO(conn).list();

        Account account = null;

        for(Account a : accounts) {
            if (number.equals(a.getNumber())) {
                account = a;
                break;
            }
        }
        if(account == null)
            throw new IllegalArgumentException("Não existe conta cadastrada com esse número!");

        return account;
    }

    private static void transferAmount() {
        System.out.println("Informe o número da conta de origem:");
        Integer originAccountNumber = sc.nextInt();

        System.out.println("Informe o número da conta de destino:");
        Integer destinationAccountNumber = sc.nextInt();

        System.out.println("Informe o valor:");
        BigDecimal amount = sc.nextBigDecimal();

        //atribui os numeros as suas respectivas contas
        Account originAccount = searchAccountByNumber(originAccountNumber);
        Account destinationAccount = searchAccountByNumber(destinationAccountNumber);

        BigDecimal newOriginAccountBalance = originAccount.getBalance().subtract(amount);
        BigDecimal newDestinationAccountBalance = destinationAccount.getBalance().add(amount);

        Connection conn1 = new ConnectionFactory().getConnection();

        //saque na conta de origem
        new AccountDAO(conn1).update(originAccount.getNumber(), newOriginAccountBalance);

        Connection conn2 = new ConnectionFactory().getConnection();

        //deposito na conta de destino
        new AccountDAO(conn2).update(destinationAccount.getNumber(), newDestinationAccountBalance);

        System.out.println("Transferência realizada com sucesso!");
    }

    private static void deleteAccount() {
        System.out.println("Informe o número da conta a ser excluída:");
        Integer number = sc.nextInt();

        //se nenhuma conta for encontrada com o numero, lança uma exceçao
        searchAccountByNumber(number);

        Connection conn = new ConnectionFactory().getConnection();

        new AccountDAO(conn).delete(number);

        System.out.println("Conta excluída com sucesso!");
    }
}
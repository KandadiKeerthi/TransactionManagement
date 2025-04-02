package com.example.TransactionManagementSystem.entities;

import java.util.List;
import java.util.stream.Collectors;

public class BalanceDto {

    private double totalAmount;
    private double expenses;
    private double balance;

    public BalanceDto(List<Entities> entities) {
//        double salary = 0;
//        double debit = 0;
//        double credit = 0;
//        for (Entities entities1 : entities) {
//            if (entities1.getTransactionType().equals("salary")) {
//                salary = salary + entities1.getAmount();
//            } else if (entities1.getTransactionType().equals("debit")) {
//                debit = debit + entities1.getAmount();
//            } else if (entities1.getTransactionType().equals("credit")) {
//                credit = credit + entities1.getAmount();
//            }
//        }
        double salary = entities.stream()
                .filter(entities1 -> entities1.getTransactionType().equalsIgnoreCase("salary"))
                .mapToDouble(Entities::getAmount)
                .sum();
        System.out.println(salary);

        double debit = entities.stream()
                .filter(entities1 -> entities1.getTransactionType().equalsIgnoreCase("debit"))
                .mapToDouble(Entities::getAmount)
                .sum();
        System.out.println(debit);

        double credit = entities.stream()
                .filter(entities1 -> entities1.getTransactionType().equalsIgnoreCase("credit"))
                .mapToDouble(Entities::getAmount)
                .sum();

        this.totalAmount = (salary + credit);
        this.expenses = (debit);
        this.balance = ((salary + credit) - debit);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

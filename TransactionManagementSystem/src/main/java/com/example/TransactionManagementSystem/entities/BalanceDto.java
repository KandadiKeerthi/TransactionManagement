package com.example.TransactionManagementSystem.entities;

import java.util.List;

public class BalanceDto {

    private double totalAmount;
    private double expenses;
    private double balance;

    public BalanceDto(List<Entities> entities) {
        double salary = 0;
        double debit = 0;
        double credit = 0;
        for (Entities entities1 : entities) {
            if (entities1.getTransactionType().equals("salary")) {
                salary = salary + entities1.getAmount();
            } else if (entities1.getTransactionType().equals("debit")) {
                debit = debit + entities1.getAmount();
            } else if (entities1.getTransactionType().equals("credit")) {
                credit = credit + entities1.getAmount();
            }
        }
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

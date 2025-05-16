package com.example.TransactionManagementSystem.entities;

import java.util.List;

public class UserTransactions {

    private String userName;
    private List<TransactionDto> transactions;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}

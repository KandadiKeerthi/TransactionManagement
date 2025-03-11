package com.example.TransactionManagementSystem.Entities;

import java.time.LocalDate;
import java.util.Date;

public class TransactionEntity {

    private String category;
    private String name;
    private String source;
    private LocalDate date;
    private Long amount;
    private String transactionType;

    public TransactionEntity() {
    }

    public TransactionEntity(Entities entities) {
        this.category = entities.getCategory();
        this.name = entities.getName();
        this.source = entities.getSource();
        this.date = entities.getDate();
        this.amount = entities.getAmount();
        this.transactionType = entities.getTransactionType();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}

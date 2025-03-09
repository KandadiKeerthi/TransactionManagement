package com.example.TransactionManagementSystem.Entities;

import java.util.Date;

public class TransactionEntity {

    private final String category;
    private final String name;
    private final String source;
    private final Date date;
    private final Long amount;

    public TransactionEntity(Entities entities) {
        this.category = entities.getCategory();
        this.name = entities.getName();
        this.source = entities.getSource();
        this.date = entities.getDate();
        this.amount = entities.getAmount();
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public Date getDate() {
        return date;
    }

    public Long getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "TransactionEntity{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                '}';
    }


}

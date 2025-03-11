package com.example.TransactionManagementSystem;

import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void addTransaction(List<Entities> entities) {
        transactionRepository.saveAll(entities);
        System.out.println("After " + transactionRepository.findAll());
    }

    public List<TransactionEntity> getAllTransactions() {
        List<Entities> entitiesList = transactionRepository.findAll();
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        for (Entities entities : entitiesList) {
            TransactionEntity transactionEntity = new TransactionEntity(entities);
            transactionEntities.add(transactionEntity);
        }
        return transactionEntities;
    }

    public List<TransactionEntity> getByCategory(String category) {
        List<Entities> entities = transactionRepository.findByCategory(category);
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        for (Entities entities1 : entities) {
            TransactionEntity transactionEntity = new TransactionEntity(entities1);
            transactionEntities.add(transactionEntity);
        }
        return transactionEntities;
    }

    public List<Entities> TransactionEntityToEntity(List<TransactionEntity> transactionEntities) {
        List<Entities> entities = new ArrayList<>();

        for (TransactionEntity transaction : transactionEntities) {
            Entities entities1 = new Entities();
            entities1.setCategory(transaction.getCategory());
            entities1.setDate(transaction.getDate());
            entities1.setAmount(transaction.getAmount());
            entities1.setName(transaction.getName());
            entities1.setSource(transaction.getSource());

            entities.add(entities1);
        }
        return entities;
    }

    public void getBalance(){
        List<Entities> salaryTransaction = transactionRepository.findByTransactionType("salary");
        List<Entities> creditTransaction = transactionRepository.findByTransactionType("credit");
        List<Entities> debitTransaction = transactionRepository.findByTransactionType("debit");


    }
}

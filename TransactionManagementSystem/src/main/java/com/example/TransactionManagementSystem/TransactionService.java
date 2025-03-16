package com.example.TransactionManagementSystem;

import com.example.TransactionManagementSystem.Entities.BalanceDto;
import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<TransactionEntity> getAllTransactions(String category, LocalDate from, LocalDate to) {
        List<Entities> entitiesList = transactionRepository.findAll();
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        for (Entities entities : entitiesList) {
            boolean categoryMatch = (category == null || entities.getCategory().equalsIgnoreCase(category));
            boolean fromMatch = (from == null || !entities.getDate().isBefore(from));
            boolean toMatch = (to == null || !entities.getDate().isAfter(to));

            if (categoryMatch && fromMatch && toMatch) {
                TransactionEntity transactionEntity = new TransactionEntity(entities);
                transactionEntities.add(transactionEntity);
            }
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
            entities1.setTransactionType(transaction.getTransactionType());

            entities.add(entities1);
        }
        return entities;
    }

    public BalanceDto getBalanceByCategory(String category, LocalDate from, LocalDate to) {
        List<Entities> entities = transactionRepository.findAll();
        List<Entities> entitiesList = new ArrayList<>();
        for (Entities entities1 : entities) {
            boolean categoryMatch = (category == null || entities1.getCategory().equalsIgnoreCase(category));
            boolean fromMatch = (from == null || !entities1.getDate().isBefore(from));
            boolean toMatch = (to == null || !entities1.getDate().isAfter(to));
            if (categoryMatch && fromMatch && toMatch) {
                entitiesList.add(entities1);
            }
        }
        return new BalanceDto(entitiesList);
    }

}


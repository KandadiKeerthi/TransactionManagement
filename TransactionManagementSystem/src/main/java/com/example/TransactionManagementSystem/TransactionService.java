package com.example.TransactionManagementSystem;

import com.example.TransactionManagementSystem.Entities.BalanceDto;
import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            entities1.setTransactionType(transaction.getTransactionType());

            entities.add(entities1);
        }
        return entities;
    }

    public BalanceDto getBalance() {
        List<Entities> list = transactionRepository.findAll();
        BalanceDto balanceDto = new BalanceDto();
        double salary = 0;
        double debit = 0;
        double credit = 0;
        for (Entities entities1 : list) {
            if (entities1.getTransactionType().equals("salary")) {
                salary = salary + entities1.getAmount();
            } else if (entities1.getTransactionType().equals("debit")) {
                debit = debit + entities1.getAmount();
            } else if (entities1.getTransactionType().equals("credit")) {
                credit = credit + entities1.getAmount();
            }
        }
        balanceDto.setTotalAmount(salary + credit);
        balanceDto.setExpenses(debit);
        balanceDto.setBalance((salary + credit) - debit);

        return balanceDto;
    }

    public BalanceDto getBalanceByCategory(String category) {
        List<Entities> entities = transactionRepository.findByCategory(category);
        BalanceDto balanceDto = new BalanceDto();
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
        balanceDto.setTotalAmount(salary);
        balanceDto.setExpenses(debit);
        balanceDto.setBalance((salary + credit) - debit);

        return balanceDto;
    }

    public BalanceDto getBalanceByMonth(int month, int year){
        List<Entities> entities = transactionRepository.findAll();
        BalanceDto balanceDto = new BalanceDto();
        double salary = 0;
        double debit = 0;
        double credit = 0;

        for (Entities entities1 : entities){
            LocalDate date = entities1.getDate();

            if (date.getMonthValue() == month && date.getYear() == year){
                if (entities1.getTransactionType().equals("salary")) {
                    salary = salary + entities1.getAmount();
                } else if (entities1.getTransactionType().equals("debit")) {
                    debit = debit + entities1.getAmount();
                } else if (entities1.getTransactionType().equals("credit")) {
                    credit = credit + entities1.getAmount();
                }

            }
        }
        balanceDto.setTotalAmount(salary);
        balanceDto.setExpenses(debit);
        balanceDto.setBalance((salary + credit) - debit);

        return balanceDto;
    }
}


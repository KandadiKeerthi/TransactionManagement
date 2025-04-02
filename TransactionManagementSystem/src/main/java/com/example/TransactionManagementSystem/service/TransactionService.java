package com.example.TransactionManagementSystem.service;

import com.example.TransactionManagementSystem.entities.BalanceDto;
import com.example.TransactionManagementSystem.entities.Entities;
import com.example.TransactionManagementSystem.entities.TransactionDto;
import com.example.TransactionManagementSystem.repository.TransactionRepository;
import com.example.TransactionManagementSystem.utils.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void addTransaction(List<Entities> entities) {
        transactionRepository.saveAll(entities);
    }

    public List<TransactionDto> getAllTransactions(String category, LocalDate from, LocalDate to) {
        List<Entities> entitiesList = transactionRepository.findAll();

        Stream<Entities> filterResult = entitiesList.stream();
        if (Objects.nonNull(category))
            filterResult = Filters.filterCategory(filterResult, category);

        if (Objects.nonNull(from))
            filterResult = Filters.filterFromDate(filterResult, from);

        if (Objects.nonNull(to))
            filterResult = Filters.filterToDate(filterResult, to);

        return filterResult.map(TransactionDto::new).toList();
    }

    public List<TransactionDto> getByCategory(String category) {
        List<Entities> entities = transactionRepository.findByCategory(category);
        List<TransactionDto> transactionEntities = new ArrayList<>();
        for (Entities entities1 : entities) {
            TransactionDto transactionDto = new TransactionDto(entities1);
            transactionEntities.add(transactionDto);
        }
        return transactionEntities;
    }

    public List<Entities> transactionEntityToEntity(List<TransactionDto> transactionEntities) {
        List<Entities> entities = new ArrayList<>();

        for (TransactionDto transaction : transactionEntities) {
            Entities entity = new Entities();
            entity.setCategory(transaction.getCategory());
            entity.setDate(transaction.getDate());
            entity.setAmount(transaction.getAmount());
            entity.setName(transaction.getName());
            entity.setSource(transaction.getSource());
            entity.setTransactionType(transaction.getTransactionType());

            entities.add(entity);
        }
        return entities;
    }

    public BalanceDto getBalanceByCategory(String category, LocalDate from, LocalDate to) {
        List<Entities> entities = transactionRepository.findAll();

        Stream<Entities> filterResult = entities.stream();
        if (Objects.nonNull(category))
            filterResult = Filters.filterCategory(filterResult, category);

        if (Objects.nonNull(from))
            filterResult = Filters.filterFromDate(filterResult, from);

        if (Objects.nonNull(to))
            filterResult = Filters.filterToDate(filterResult, to);
        return new BalanceDto(filterResult.toList());
    }

}


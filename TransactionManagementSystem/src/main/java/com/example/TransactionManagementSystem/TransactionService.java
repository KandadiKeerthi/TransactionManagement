package com.example.TransactionManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void addTransaction(List<Entities> entities){

        transactionRepository.saveAll(entities);
        System.out.println("After " +transactionRepository.findAll());
    }

    public List<Entities> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Entities> getByCategory(String category){
        return transactionRepository.findByCategory(category);
    }
}

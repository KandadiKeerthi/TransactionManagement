package com.example.TransactionManagementSystem.validations;

import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import com.example.TransactionManagementSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationHelper {

    private static final List<String> validCategories = List.of("Dining", "Gas","Salary","Travel","Zelle");

    public String validateTransaction(TransactionEntity transactionEntity){
        if (transactionEntity.getAmount() == null || transactionEntity.getAmount() <=0){
            return "Amount should be grater than 0";
        }
        if (!validCategories.contains(transactionEntity.getCategory())){
            return "Category not found";
        }
        if (transactionEntity.getCategory() == null || transactionEntity.getCategory().isEmpty()){
            return "Category cannot be empty";
        }
        return "Valid Transaction";
    }

}

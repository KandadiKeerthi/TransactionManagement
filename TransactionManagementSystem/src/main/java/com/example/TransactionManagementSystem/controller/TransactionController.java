package com.example.TransactionManagementSystem.controller;

import com.example.TransactionManagementSystem.Entities.BalanceDto;
import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import com.example.TransactionManagementSystem.service.TransactionService;
import com.example.TransactionManagementSystem.validations.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    ValidationHelper validationHelper;

    @PostMapping
    public ResponseEntity<String> addTransactions(@Validated @RequestBody List<TransactionEntity> entities) {
        List<Entities> entities1 = transactionService.TransactionEntityToEntity(entities);
        transactionService.addTransaction(entities1);
        return ResponseEntity.ok("Added Successfully");
    }

    @GetMapping
    public List<TransactionEntity> getAllTransaction(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        return transactionService.getAllTransactions(category, from, to);
    }

    @GetMapping("/{category}")
    public List<TransactionEntity> getByCategory(@PathVariable String category) {
        return transactionService.getByCategory(category);
    }

    @GetMapping("/balance")
    public BalanceDto getBalance(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) LocalDate from,
                                 @RequestParam(required = false) LocalDate to) {
        return transactionService.getBalanceByCategory(category,from,to);

    }


}

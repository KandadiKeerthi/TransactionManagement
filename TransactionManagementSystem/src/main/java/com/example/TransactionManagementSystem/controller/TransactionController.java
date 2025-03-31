package com.example.TransactionManagementSystem.controller;

import com.example.TransactionManagementSystem.entities.BalanceDto;
import com.example.TransactionManagementSystem.entities.Entities;
import com.example.TransactionManagementSystem.entities.TransactionDto;
import com.example.TransactionManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.TransactionManagementSystem.constants.SuccessMessages.TRANSACTION_ADD_SUCCESS;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> addTransactions(@Validated @RequestBody List<TransactionDto> entities) {
        List<Entities> entities1 = transactionService.transactionEntityToEntity(entities);
        transactionService.addTransaction(entities1);
        return ResponseEntity.ok(TRANSACTION_ADD_SUCCESS);
    }

    @GetMapping
    public List<TransactionDto> getAllTransaction(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        return transactionService.getAllTransactions(category, from, to);
    }

    @GetMapping("/{category}")
    public List<TransactionDto> getByCategory(@PathVariable String category) {
        return transactionService.getByCategory(category);
    }

    @GetMapping("/balance")
    public BalanceDto getBalance(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) LocalDate from,
                                 @RequestParam(required = false) LocalDate to) {
        return transactionService.getBalanceByCategory(category,from,to);
    }
}

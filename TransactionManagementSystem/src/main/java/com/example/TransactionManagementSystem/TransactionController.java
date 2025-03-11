package com.example.TransactionManagementSystem;

import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/trans")
    public String addTransactions(@RequestBody List<TransactionEntity> entities) {
        List<Entities> entities1 = transactionService.TransactionEntityToEntity(entities);
        transactionService.addTransaction(entities1);
        return "Added Successfully";
    }

    @GetMapping
    public List<TransactionEntity> getAllTransaction() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{category}")
    public List<TransactionEntity> getByCategory(@PathVariable String category) {
        return transactionService.getByCategory(category);
    }
}

package com.example.TransactionManagementSystem;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/trans")
    public String addTransactions(@RequestBody List<Entities> entities){
        System.out.println("Before " +entities);
        transactionService.addTransaction(entities);
        return "Added Successfully";
    }

    @GetMapping
    public List<Entities> getAllTransaction(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{category}")
    public List<Entities> getByCategory(@PathVariable String category){
        return transactionService.getByCategory(category);
    }
}

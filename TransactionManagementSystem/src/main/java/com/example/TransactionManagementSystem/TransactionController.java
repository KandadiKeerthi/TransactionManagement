package com.example.TransactionManagementSystem;

import com.example.TransactionManagementSystem.Entities.BalanceDto;
import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
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

    @GetMapping("/balance")
    public BalanceDto getBalance(){
        return transactionService.getBalance();
    }

    @GetMapping("/{category}/balance")
    public BalanceDto getBalanceByCategory(@PathVariable String category){
        return transactionService.getBalanceByCategory(category);
    }

    @GetMapping("/balance/{month}")
    public BalanceDto getBalanceByMonth(@PathVariable String month){
        int monthNumber = convertMonthToNumber(month);
        int currentYear = LocalDate.now().getYear();

        return transactionService.getBalanceByMonth(monthNumber, currentYear);
    }

    private int convertMonthToNumber(String month){
        return Month.valueOf(month.toUpperCase()).getValue();
    }
}

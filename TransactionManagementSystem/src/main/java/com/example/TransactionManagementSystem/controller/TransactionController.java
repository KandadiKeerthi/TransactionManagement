package com.example.TransactionManagementSystem.controller;

import com.example.TransactionManagementSystem.entities.*;
import com.example.TransactionManagementSystem.service.TransactionService;
import com.example.TransactionManagementSystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<String> addTransactions(@Validated @RequestBody UserTransactions userTransactions) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        List<Entities> entities1 = transactionService.transactionEntityToEntity(userTransactions.getTransactions());
        transactionService.addTransaction(currentUser.getUsername(), entities1);
        return ResponseEntity.ok(TRANSACTION_ADD_SUCCESS);
    }

    @GetMapping("/user/{userName}")
    public List<TransactionDto> getAllTransaction(
            @PathVariable String userName,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        return transactionService.getAllTransactions(userName,category, from, to);
    }

    @GetMapping("/{category}")
    public List<TransactionDto> getByCategory(@PathVariable String category) {
        return transactionService.getByCategory(category);
    }

    @GetMapping("/balance/{userName}")
    public BalanceDto getBalance(
            @PathVariable String userName,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {
        return transactionService.getBalanceByUser(userName, category,from,to);
    }
}

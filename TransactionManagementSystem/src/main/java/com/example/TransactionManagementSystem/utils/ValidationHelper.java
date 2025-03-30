package com.example.TransactionManagementSystem.utils;

import com.example.TransactionManagementSystem.entities.TransactionDto;

import java.util.Optional;

import static com.example.TransactionManagementSystem.constants.ErrorMessages.*;
import static com.example.TransactionManagementSystem.constants.TransactionConstants.VALID_CATEGORIES;


public class ValidationHelper {

    public static Optional<String> validateTransaction(TransactionDto transactionDto){
        if (transactionDto.getAmount() == null || transactionDto.getAmount() <= 0){
            return Optional.of(AMOUNT_IS_ZERO);
        }
        if (!VALID_CATEGORIES.contains(transactionDto.getCategory())){
            return Optional.of(CATEGORY_NOT_FOUND);
        }
        if (transactionDto.getCategory() == null || transactionDto.getCategory().isEmpty()){
            return Optional.of(CATEGORY_EMPTY);
        }
        return Optional.empty();
    }

}

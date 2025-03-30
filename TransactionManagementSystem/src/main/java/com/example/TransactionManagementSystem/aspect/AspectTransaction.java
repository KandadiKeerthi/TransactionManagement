package com.example.TransactionManagementSystem.aspect;

import com.example.TransactionManagementSystem.entities.TransactionDto;
import com.example.TransactionManagementSystem.exceptions.InvalidDataException;
import com.example.TransactionManagementSystem.utils.ValidationHelper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Aspect
public class AspectTransaction {

    @Before("execution(* com.example.TransactionManagementSystem.controller.TransactionController.addTransactions(..)) && args(transactionEntities)")
    public void validation(List<TransactionDto> transactionEntities) {
        for (TransactionDto entities1 : transactionEntities){
            Optional<String> responseEntity = ValidationHelper.validateTransaction(entities1);
            if (responseEntity.isPresent()){
                throw new InvalidDataException(responseEntity.get());
            }
        }
    }
}

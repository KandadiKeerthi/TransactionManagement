package com.example.TransactionManagementSystem.aspect;

import com.example.TransactionManagementSystem.Entities.Entities;
import com.example.TransactionManagementSystem.Entities.TransactionEntity;
import com.example.TransactionManagementSystem.exceptions.InvalidDataException;
import com.example.TransactionManagementSystem.validations.ValidationHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class AspectTransaction {

    @Autowired
    ValidationHelper validationHelper;

    @Before("execution(* com.example.TransactionManagementSystem.controller.TransactionController.addTransactions(..)) && args(transactionEntities)")
    public void validation(List<TransactionEntity> transactionEntities){

        System.out.println("working");

        for (TransactionEntity entities1 : transactionEntities){

            String responseEntity = validationHelper.validateTransaction(entities1);
            if (!"Valid Transaction".equalsIgnoreCase(responseEntity)){
                throw new InvalidDataException(responseEntity);
            }
        }

    }
}

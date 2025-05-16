package com.example.TransactionManagementSystem.repository;

import com.example.TransactionManagementSystem.entities.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Entities, Integer> {
    List<Entities> findByCategory(String category);

}

package com.example.TransactionManagementSystem;

import com.example.TransactionManagementSystem.Entities.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Entities, Integer> {

    List<Entities> findByCategory(String category);

}

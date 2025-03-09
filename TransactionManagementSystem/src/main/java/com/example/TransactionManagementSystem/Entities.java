package com.example.TransactionManagementSystem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Entities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    private Date date;
    private String category;
    private String name;
    private String source;
    private Long amount;

    @Override
    public String toString() {
        return "Entities{" +
                "id=" + id +
//                ", date=" + date +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                '}';
    }
}

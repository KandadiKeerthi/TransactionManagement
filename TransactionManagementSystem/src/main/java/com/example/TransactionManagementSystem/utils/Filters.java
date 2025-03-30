package com.example.TransactionManagementSystem.utils;

import com.example.TransactionManagementSystem.entities.Entities;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Filters {
    public static Stream<Entities> filterCategory(Stream<Entities> input, String category) {
        return input.filter(element -> element.getCategory().equalsIgnoreCase(category));
    }

    public static Stream<Entities> filterFromDate(Stream<Entities> input, LocalDate date) {
        return input.filter(element -> date == null || !element.getDate().isBefore(date));
    }

    public static Stream<Entities> filterToDate(Stream<Entities> input, LocalDate date) {
        return input.filter(element -> date == null || !element.getDate().isAfter(date));
    }
}

package com.example.emt.model;

import lombok.Data;

@Data
public class BookDto {

    String name;

    Category category;

    Long author;

    Integer availableCopies;

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}

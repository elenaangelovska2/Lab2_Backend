package com.example.emt.service;



import com.example.emt.model.Author;
import com.example.emt.model.Book;
import com.example.emt.model.BookDto;
import com.example.emt.model.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Author author, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name,  Category category, Author author, Integer availableCopies);
    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);
}

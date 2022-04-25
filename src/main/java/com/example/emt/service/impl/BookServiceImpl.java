package com.example.emt.service.impl;

import com.example.emt.exceptions.AuthorNotFoundException;
import com.example.emt.exceptions.BookNotFoundException;
import com.example.emt.model.Author;
import com.example.emt.model.Book;
import com.example.emt.model.BookDto;
import com.example.emt.model.Category;
import com.example.emt.repository.AuthorRepository;
import com.example.emt.repository.BookRepository;
import com.example.emt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Author author, Integer availableCopies) {

        Book book = new Book(name, category, author, availableCopies);

         this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException());

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        //this.refreshMaterializedView();

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Author author, Integer availableCopies) {
        Book book=this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public void deleteById(Long id) {
    this.bookRepository.deleteById(id);
    }
}

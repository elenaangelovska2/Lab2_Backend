package com.example.emt.service.impl;

import com.example.emt.exceptions.AuthorNotFoundException;
import com.example.emt.model.Author;
import com.example.emt.model.Country;
import com.example.emt.repository.AuthorRepository;
import com.example.emt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Country country) {
       Author author= new Author(name, surname, country);

       this.authorRepository.save(author);

       return Optional.of(author);

    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Country country) {
        Author author= this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException());

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
    this.authorRepository.deleteById(id);
    }
}

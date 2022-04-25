package com.example.emt.service;


import com.example.emt.model.Author;
import com.example.emt.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Country country);

    Optional<Author> edit(Long id, String name, String surname, Country country);

    void deleteById(Long id);

}

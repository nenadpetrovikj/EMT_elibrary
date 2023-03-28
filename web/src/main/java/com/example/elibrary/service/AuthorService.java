package com.example.elibrary.service;

import com.example.elibrary.model.Author;
import com.example.elibrary.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
    Author findById(Long id);
    Author create(String name, String surname, Long countryId);
    Author create(AuthorDto authorDto);
}

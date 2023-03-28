package com.example.elibrary.service.impl;

import com.example.elibrary.model.Author;
import com.example.elibrary.model.Country;
import com.example.elibrary.model.dto.AuthorDto;
import com.example.elibrary.model.exceptions.InvalidAuthorIdException;
import com.example.elibrary.model.exceptions.InvalidCountryIdException;
import com.example.elibrary.repository.AuthorRepository;
import com.example.elibrary.repository.CountryRepository;
import com.example.elibrary.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(InvalidCountryIdException::new);
        Author author = new Author(name, surname, country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author create(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountryId()).orElseThrow(InvalidCountryIdException::new);
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        return this.authorRepository.save(author);
    }
}

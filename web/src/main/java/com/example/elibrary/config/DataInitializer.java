package com.example.elibrary.config;

import com.example.elibrary.model.dto.AuthorDto;
import com.example.elibrary.model.dto.BookDto;
import com.example.elibrary.model.dto.CountryDto;
import com.example.elibrary.model.enumerations.Category;
import com.example.elibrary.service.AuthorService;
import com.example.elibrary.service.BookService;
import com.example.elibrary.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    private Category randomizeCategory(int number) {
        if (number % 7 == 1) return Category.NOVEL;
        if (number % 7 == 2) return Category.THRILER;
        if (number % 7 == 3) return Category.HISTORY;
        if (number % 7 == 4) return Category.FANTASY;
        if (number % 7 == 5) return Category.BIOGRAPHY;
        if (number % 7 == 6) return Category.CLASSICS;
        return Category.DRAMA;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 11; i++) {
            CountryDto countryDto = new CountryDto("Country " + i, "Continent " + i);
            this.countryService.create(countryDto);
            AuthorDto authorDto = new AuthorDto("AuthorName " + i, "AuthorSurname " + i, countryService.findAll().get(i-1).getId());
            this.authorService.create(authorDto);
            BookDto bookDto = new BookDto("BookName " + i, randomizeCategory(i), authorService.findAll().get(i-1).getId(), i*100);
            this.bookService.create(bookDto);
        }
    }
}

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
        if (number == 1) return Category.DRAMA;
        if (number == 2) return Category.BIOGRAPHY;
        return Category.CLASSICS;
    }

    @PostConstruct
    public void initData() {
//        for (int i = 1; i < 4; i++) {
//            Country country = this.countryService.create("Country " + i, "Continent " + i);
//            Author author = this.authorService.create("AuthorName " + i, "AuthorSurname " + i, country.getId());
//            bookService.create("BookName " + i, randomizeCategory(i), author.getId(), i*100);
//        }
        for (int i = 1; i < 4; i++) {
            CountryDto countryDto = new CountryDto("Country " + i, "Continent " + i);
            this.countryService.create(countryDto);
            AuthorDto authorDto = new AuthorDto("AuthorName " + i, "AuthorSurname " + i, countryService.findAll().get(i-1).getId());
            this.authorService.create(authorDto);
            BookDto bookDto = new BookDto("BookName " + i, randomizeCategory(i), authorService.findAll().get(i-1).getId(), i*100);
            this.bookService.create(bookDto);
        }
    }
}

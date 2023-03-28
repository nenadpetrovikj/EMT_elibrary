package com.example.elibrary.service;

import com.example.elibrary.model.Book;
import com.example.elibrary.model.dto.BookDto;
import com.example.elibrary.model.enumerations.Category;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book findById(Long id);
    Book create(String name, Category categoryType, Long authorId, Integer availableCopies);
    Book create(BookDto bookDto);
    Book update(Long id, String name, Category categoryType, Long authorId, Integer availableCopies);
    Book update(Long id, BookDto bookDto);
    Book deleteById(Long id);
    Book mark(Long id);
}

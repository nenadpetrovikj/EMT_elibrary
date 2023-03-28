package com.example.elibrary.service.impl;

import com.example.elibrary.model.Author;
import com.example.elibrary.model.Book;
import com.example.elibrary.model.dto.BookDto;
import com.example.elibrary.model.enumerations.Category;
import com.example.elibrary.model.exceptions.InvalidAuthorIdException;
import com.example.elibrary.model.exceptions.InvalidBookIdException;
import com.example.elibrary.repository.AuthorRepository;
import com.example.elibrary.repository.BookRepository;
import com.example.elibrary.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Book create(String name, Category categoryType, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        Book book = new Book(name, categoryType, author, availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book create(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(InvalidAuthorIdException::new);
        Book book = new Book(bookDto.getName(), bookDto.getCategoryType(), author, bookDto.getAvailableCopies());
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String name, Category categoryType, Long authorId, Integer availableCopies) {
        Book book = findById(id);
        book.setName(name);
        book.setCategoryType(categoryType);
        Author author = this.authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, BookDto bookDto) {
        Book book = findById(id);
        book.setName(bookDto.getName());
        book.setCategoryType(bookDto.getCategoryType());
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(InvalidAuthorIdException::new);
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return this.bookRepository.save(book);
    }

    @Override
    public Book deleteById(Long id) {
        Book book = findById(id);
        this.bookRepository.delete(book);
        return book;
    }

    @Override
    public Book mark(Long id) {
        Book book = findById(id);
        Integer remainingCopies = book.getAvailableCopies();
        if (remainingCopies > 0) remainingCopies--;
        book.setAvailableCopies(remainingCopies);
        return this.bookRepository.save(book);
    }
}

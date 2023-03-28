package com.example.elibrary.rest;

import com.example.elibrary.model.Book;
import com.example.elibrary.model.dto.BookDto;
import com.example.elibrary.model.enumerations.Category;
import com.example.elibrary.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    // dali mora da se vrakja ResponseEntity? Neophodno li e toa?
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return Optional.of(this.bookService.findById(id))
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return Optional.of(this.bookService.create(bookDto))
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return Optional.of(this.bookService.update(id, bookDto))
                .map(discount -> ResponseEntity.ok().body(discount))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mark/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) {
        return Optional.of(this.bookService.mark(id))
                .map(discount -> ResponseEntity.ok().body(discount))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/categories")
    public List<Category> listAllCategories() {
        return Arrays.stream(Category.values()).toList();
    }


}

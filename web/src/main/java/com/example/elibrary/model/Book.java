package com.example.elibrary.model;

import com.example.elibrary.model.enumerations.Category;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Category categoryType;
    @ManyToOne
    Author author;
    Integer availableCopies;

    public Book(String name, Category categoryType, Author author, Integer availableCopies) {
        this.name = name;
        this.categoryType = categoryType;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {

    }
}

package com.example.elibrary.model.dto;

import com.example.elibrary.model.enumerations.Category;
import lombok.Data;

@Data
public class BookDto {

    String name;
    Category categoryType;
    Long authorId;
    Integer availableCopies;

    public BookDto(String name, Category categoryType, Long authorId, Integer availableCopies) {
        this.name = name;
        this.categoryType = categoryType;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}

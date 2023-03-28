package com.example.elibrary.model.dto;

import lombok.Data;

@Data
public class AuthorDto {

    String name;
    String surname;
    Long countryId;

    public AuthorDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }
}

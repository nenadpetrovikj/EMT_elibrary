package com.example.elibrary.model.dto;

import lombok.Data;

@Data
public class CountryDto {

    String name;
    String continent;

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}

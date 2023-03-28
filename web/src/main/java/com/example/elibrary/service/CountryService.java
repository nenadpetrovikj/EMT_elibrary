package com.example.elibrary.service;


import com.example.elibrary.model.Country;
import com.example.elibrary.model.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country create(String name, String continent);
    Country create(CountryDto countryDto);
}

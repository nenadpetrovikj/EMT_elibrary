package com.example.elibrary.service.impl;

import com.example.elibrary.model.Country;
import com.example.elibrary.model.dto.CountryDto;
import com.example.elibrary.model.exceptions.InvalidCountryIdException;
import com.example.elibrary.repository.CountryRepository;
import com.example.elibrary.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }

    @Override
    public Country create(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return this.countryRepository.save(country);
    }
}

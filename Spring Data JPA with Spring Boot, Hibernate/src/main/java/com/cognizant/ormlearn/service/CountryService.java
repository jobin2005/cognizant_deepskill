package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Country> findByNameContaining(String text) {
        return countryRepository.findByNameContaining(text);
    }

    @Transactional(readOnly = true)
    public List<Country> findByNameStartingWith(String prefix) {
        return countryRepository.findByNameStartingWith(prefix);
    }

    @Transactional(readOnly = true)
    public List<Country> findByNameContainingSorted(String text) {
        return countryRepository.findByNameContainingOrderByNameAsc(text);
    }

    @Transactional(readOnly = true)
    public List<Country> findTop3ByNameContaining(String text) {
        return countryRepository.findTop3ByNameContaining(text);
    }

    @Transactional(readOnly = true)
    public List<Country> findByCodeGreaterThan(String code) {
        return countryRepository.findByCodeGreaterThan(code);
    }

    @Transactional(readOnly = true)
    public List<Country> findByCodeLessThan(String code) {
        return countryRepository.findByCodeLessThan(code);
    }

    @Transactional(readOnly = true)
    public List<Country> findByCreatedDateBetween(LocalDate start, LocalDate end) {
        return countryRepository.findByCreatedDateBetween(start, end);
    }
}

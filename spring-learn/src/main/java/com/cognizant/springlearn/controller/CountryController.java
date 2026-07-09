package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        LOGGER.info("getCountryIndia() - start");
        Country c = countryService.getCountryIndia();
        LOGGER.info("getCountryIndia() - end");
        return c;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("getAllCountries() - start");
        List<Country> list = countryService.getAllCountries();
        LOGGER.info("getAllCountries() - end");
        return list;
    }

    @GetMapping("/countries/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code) {
        LOGGER.info("getCountry(code={}) - start", code);
        return countryService.getCountry(code)
                .map(c -> {
                    LOGGER.info("getCountry - found {}", c);
                    return ResponseEntity.ok(c);
                })
                .orElseGet(() -> {
                    LOGGER.info("getCountry - not found for code {}", code);
                    return ResponseEntity.notFound().build();
                });
    }
}

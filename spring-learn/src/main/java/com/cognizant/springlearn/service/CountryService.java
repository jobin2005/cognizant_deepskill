package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Object bean = context.getBean("countryList");
            if (bean instanceof List) {
                return new ArrayList<>((List<Country>) bean);
            }
        } catch (Exception e) {
            LOGGER.error("Error loading country list", e);
        }
        return new ArrayList<>();
    }

    public Optional<Country> getCountry(String code) {
        List<Country> countries = getAllCountries();
        if (code == null) return Optional.empty();
        String codeUpper = code.trim().toUpperCase();
        return countries.stream()
                .filter(c -> codeUpper.equalsIgnoreCase((c.getCode() == null) ? "" : c.getCode()))
                .findFirst();
    }

    public Country getCountryIndia() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            return context.getBean("country", Country.class);
        } catch (Exception e) {
            LOGGER.error("Error loading India country bean", e);
            return null;
        }
    }
}

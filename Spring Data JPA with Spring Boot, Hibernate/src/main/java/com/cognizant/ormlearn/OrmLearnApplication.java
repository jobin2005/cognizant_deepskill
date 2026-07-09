package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        LOGGER.info("Inside main");
        testGetAllCountries();
        testQueryMethods();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start getAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End getAllCountries");
    }

    private static void testQueryMethods() {
        LOGGER.info("Start query methods demo");

        LOGGER.info("findByNameContaining('Uni')");
        LOGGER.debug("result={}", countryService.findByNameContaining("Uni"));

        LOGGER.info("findByNameStartingWith('I')");
        LOGGER.debug("result={}", countryService.findByNameStartingWith("I"));

        LOGGER.info("findByNameContainingOrderByNameAsc('a')");
        LOGGER.debug("result={}", countryService.findByNameContainingSorted("a"));

        LOGGER.info("findTop3ByNameContaining('a')");
        LOGGER.debug("result={}", countryService.findTop3ByNameContaining("a"));

        LOGGER.info("findByCodeGreaterThan('IN')");
        LOGGER.debug("result={}", countryService.findByCodeGreaterThan("IN"));

        LOGGER.info("findByCodeLessThan('IN')");
        LOGGER.debug("result={}", countryService.findByCodeLessThan("IN"));

        LOGGER.info("findByCreatedDateBetween(2020-01-01, 2022-12-31)");
        LOGGER.debug("result={}", countryService.findByCreatedDateBetween(
                java.time.LocalDate.of(2020, 1, 1),
                java.time.LocalDate.of(2022, 12, 31)));

        LOGGER.info("End query methods demo");
    }
}

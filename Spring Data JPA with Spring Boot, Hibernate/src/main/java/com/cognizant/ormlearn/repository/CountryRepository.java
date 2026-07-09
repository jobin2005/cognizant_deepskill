package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    List<Country> findByNameContaining(String text);

    List<Country> findByNameStartingWith(String prefix);

    List<Country> findByNameContainingOrderByNameAsc(String text);

    List<Country> findTop3ByNameContaining(String text);

    List<Country> findByCodeGreaterThan(String code);

    List<Country> findByCodeLessThan(String code);

    List<Country> findByCreatedDateBetween(LocalDate start, LocalDate end);
}

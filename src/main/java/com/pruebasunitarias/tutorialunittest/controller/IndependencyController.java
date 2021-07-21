package com.pruebasunitarias.tutorialunittest.controller;

import com.pruebasunitarias.tutorialunittest.models.Country;
import com.pruebasunitarias.tutorialunittest.models.CountryResponse;
import com.pruebasunitarias.tutorialunittest.repositories.CountryRepository;
import com.pruebasunitarias.tutorialunittest.util.DiffBetweenDates;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;
import java.util.Optional;

@RestController()
public class IndependencyController {

    CountryResponse countryResponse;
    Optional<Country> country;
    CountryRepository countryRepository;
    DiffBetweenDates diffBetweenDates;

    public IndependencyController(CountryRepository countryRepository, DiffBetweenDates diffBetweenDates) {
        this.countryRepository = countryRepository;
        this.diffBetweenDates = diffBetweenDates;
    }

    @GetMapping(path = "/country/{countryId}")
    public ResponseEntity<CountryResponse> getCountryDetails(@PathVariable("countryId") String countryId) {
        country = Optional.of(new Country());
        countryResponse = new CountryResponse();

        country = Optional.ofNullable(countryRepository.findCountryByIsoCode(countryId.toUpperCase()));

        if (country.isPresent()) {
            Period period = diffBetweenDates.calculateYearsOfIndependency(country.get().getCountryIdependenceDate());
            countryResponse.setCountryName(country.get().getCountryName());
            countryResponse.setCapitalName(country.get().getCountryCapital());
            countryResponse.setIndependenceDate(country.get().getCountryIdependenceDate());
            countryResponse.setDayssOfIndependency(period.getDays());
            countryResponse.setMonthsOfIndependency(period.getMonths());
            countryResponse.setYearsOfIndependency(period.getYears());
        }
        return ResponseEntity.ok(countryResponse);
    }
}
package com.pruebasunitarias.tutorialunittest.controller;

import com.pruebasunitarias.tutorialunittest.models.Country;
import com.pruebasunitarias.tutorialunittest.models.CountryResponse;
import com.pruebasunitarias.tutorialunittest.repositories.CountryRepository;
import com.pruebasunitarias.tutorialunittest.util.DiffBetweenDates;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


class IndependencyControllerTest {

  @Autowired
  CountryResponse countryResponse;

  @Autowired
  Optional<Country> country;

  @Autowired
  DiffBetweenDates diffBetweenDates = new DiffBetweenDates();


  @Autowired
  CountryRepository countryRepositoryMock = Mockito.mock(CountryRepository.class);

  @Autowired
  IndependencyController independencyController = new IndependencyController(countryRepositoryMock,
          diffBetweenDates);

  @BeforeEach
  //Arrange
  void setUp() {
    System.out.println("Inicio Mock");
    System.out.println("-------------");
    Country mockCountry = new Country();
    mockCountry.setIsoCode("CO");
    mockCountry.setCountryCapital("Bogota");
    mockCountry.setCountryId((long) 1);
    mockCountry.setCountryName("Colombia");
    mockCountry.setCountryIdependenceDate("10/10/2000");

    Mockito.when(countryRepositoryMock.findCountryByIsoCode("CO")).thenReturn(mockCountry);

  }


  @Test
  void getCountryDetails() {
    //Act
    ResponseEntity<CountryResponse> responseServices = independencyController
        .getCountryDetails("CO");

    System.out.println(responseServices);
    System.out.println(responseServices.getBody().getCountryName());
    System.out.println(responseServices.getBody().getCapitalName());
    System.out.println(responseServices.getBody().getIndependenceDate());
    System.out.println(responseServices.getBody().getYearsOfIndependency());
    System.out.println(responseServices.getBody().getMonthsOfIndependency());
    System.out.println(responseServices.getBody().getDayssOfIndependency());

    //Assert
    Assertions.assertEquals("Colombia", responseServices.getBody().getCountryName());
    Assertions.assertEquals(200, responseServices.getStatusCode().value());
  }

  @Test
  void getInvalidCountryDetails() {
    //Act
    ResponseEntity<CountryResponse> responseServices = independencyController
        .getCountryDetails("BO");
    System.out.println(responseServices);
    System.out.println(responseServices.getBody().getCountryName());
    System.out.println(responseServices.getBody().getCapitalName());

    //Assert
    Assertions.assertEquals(null, responseServices.getBody().getCountryName());
    Assertions.assertEquals(200, responseServices.getStatusCode().value());

  }
}
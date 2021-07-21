package com.pruebasunitarias.tutorialunittest.util;

import java.time.LocalDate;
import java.time.Period;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DiffBetweenDatesTest {

  @Autowired
  DiffBetweenDates diffBetweenDates;

  @Test
  void calculateYearsOfIndependency() {
    diffBetweenDates = new DiffBetweenDates();

    LocalDate fechaActual = LocalDate.now();
    String fechaIndependencia = "10/10/2000";

    Period resultado = diffBetweenDates.calculateYearsOfIndependency(fechaIndependencia);

    Assertions.assertEquals(20, resultado.getYears());
  }

}
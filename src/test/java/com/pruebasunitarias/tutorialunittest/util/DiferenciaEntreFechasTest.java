package com.pruebasunitarias.tutorialunittest.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DiferenciaEntreFechasTest {

  @Autowired
  DiferenciaEntreFechas diferenciaEntreFechas;

  @Test
  void calculateYearsOfIndependency() {
    diferenciaEntreFechas = new DiferenciaEntreFechas();

    LocalDate fechaActual = LocalDate.now();
    String fechaIndependencia = "10/10/2000";

    Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);

    Assertions.assertEquals(20, resultado.getYears());
  }

}
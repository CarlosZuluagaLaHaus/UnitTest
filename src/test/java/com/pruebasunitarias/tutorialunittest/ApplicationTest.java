package com.pruebasunitarias.tutorialunittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class ApplicationTest {

  @Test
  void main(){
    Application.main(new String[] {});
  }

}
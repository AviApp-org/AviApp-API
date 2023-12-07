package br.com.aviapp.api.domain.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.errors.InvalidParamError;

public class ParamValidatorTest {
  @Test
  void shouldThrowIfOneParamIsNull() {
    String test = null;

    assertThrows(InvalidParamError.class, () -> {
      ParamValidator.validate(test);
    });
  }
}

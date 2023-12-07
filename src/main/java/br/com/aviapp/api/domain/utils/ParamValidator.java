package br.com.aviapp.api.domain.utils;

import br.com.aviapp.api.domain.errors.InvalidParamError;

public class ParamValidator {
  public static void validate(Object ...params) {
    for (Object object : params) {
      if (object == null || object.toString().isBlank()) {
        String field = object != null ? object.getClass().getName() : "field";
        throw new InvalidParamError(field);
      }
    }
  }
}

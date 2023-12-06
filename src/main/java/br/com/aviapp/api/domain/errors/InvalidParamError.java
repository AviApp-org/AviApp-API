package br.com.aviapp.api.domain.errors;

public class InvalidParamError extends Error {
  public InvalidParamError(String field) {
    super("Campo " + field + " inválido");
  }
}

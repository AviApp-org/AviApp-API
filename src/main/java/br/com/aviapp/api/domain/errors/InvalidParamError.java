package br.com.aviapp.api.domain.errors;

public class InvalidParamError extends Error {
  private String field;

  public InvalidParamError(String field) {
    super("Campo " + field + " inválido");
    this.field = field;
  }
}

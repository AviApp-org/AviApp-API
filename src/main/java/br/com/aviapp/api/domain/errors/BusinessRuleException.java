package br.com.aviapp.api.domain.errors;

public class BusinessRuleException extends Exception {
  public BusinessRuleException(String message) {
    super(message);
  }

  public BusinessRuleException(String message, Throwable cause) {
    super(message, cause);
  }}

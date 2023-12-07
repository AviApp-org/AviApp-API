package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import lombok.Getter;

@Getter
public class LoginBO {
  private String userName;
  private String password;

  public LoginBO(String userName, String password) throws InvalidParamError {
    this.validate(userName, password);
    this.userName = userName;
    this.password = password;
  }

  private void validate(Object ...params) {
    for (Object object : params) {
    if (object == null || object.toString().isBlank()) {
      String field = object != null ? object.getClass().getName() : "field";
      throw new InvalidParamError(field);
    } 
    }
  }
}

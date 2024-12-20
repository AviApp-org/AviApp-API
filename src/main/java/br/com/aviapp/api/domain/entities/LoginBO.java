package br.com.aviapp.api.domain.entities;

import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.domain.utils.ParamValidator;

public class LoginBO {
  private String userName;
  private String password;

  public LoginBO(String userName, String password) throws InvalidParamError {
    ParamValidator.validate(userName, password);
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
}

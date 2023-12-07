package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.errors.InvalidParamError;

public class LoginBOTest {
  
  @Test
  void shouldMatchLoginCredentials() {
    String userName = "hello";
    String password = "world";

    LoginBO sut = new LoginBO(userName, password);

    assertEquals(password, sut.getPassword());
    assertEquals(userName, sut.getUserName());
  }

  @Test
  void shouldThrowIfAParamIsBlank() {
    assertThrows(InvalidParamError.class, () -> {
      String userName = null;
      String password = "world";

      new LoginBO(userName, password);
    });
  }
}

package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginBOTest {
  
  @Test
  void shouldMatchLoginCredentials() {
    String userName = "hello";
    String password = "world";

    LoginBO sut = new LoginBO(userName, password);

    assertEquals(password, sut.getPassword());
    assertEquals(userName, sut.getUserName());
  }
}

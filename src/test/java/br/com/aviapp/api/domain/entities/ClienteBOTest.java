package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.errors.InvalidParamError;

public class ClienteBOTest {

 /*  @Test
  void shouldInactivateAClient() {
    ClientBO bo = new ClientBO(1l, "Test", "teste@teste.com", "00000000000", "000000000");
    bo.desativar();

    assertEquals(EnumStatusCliente.INATIVO, bo.getStatus());
  }

  @Test
  void shouldActivateAClient() {
    ClientBO bo = new ClientBO(1l, "Test", "teste@teste.com", "00000000000", "000000000");
    bo.ativar();

    assertEquals(EnumStatusCliente.ATIVO, bo.getStatus());
  }

  @Test
  void shouldThrowIfEmailIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "Test", null, "00000000000", "000000000");
    });
  }

  @Test
  void shouldThrowIfCnpjIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "Test", "test@email.com", null, "000000000");
    });
  }

  @Test
  void shouldThrowIfNameIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, null, "test@email.com", "00000000000", "000000000");
    });
  }

  @Test
  void shouldThrowIfTelefoneIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "Test", "test@email.com", "00000000000", null);
    });
  }

  @Test
  void shouldThrowIfParamIsBlank() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "", "test@email.com", "00000000000", "000000000");
    });
  }*/
}

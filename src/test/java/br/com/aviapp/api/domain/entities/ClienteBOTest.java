package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.errors.InvalidParamError;

public class ClienteBOTest {

  @Test
  void shouldInactivateAClient() {
    ClienteBO bo = new ClienteBO(1l, "Test", "teste@teste.com", "00000000000", "000000000");
    bo.desativar();

    assertEquals(EnumStatusCliente.INATIVO, bo.getStatus());
  }

  @Test
  void shouldActivateAClient() {
    ClienteBO bo = new ClienteBO(1l, "Test", "teste@teste.com", "00000000000", "000000000");
    bo.ativar();

    assertEquals(EnumStatusCliente.ATIVO, bo.getStatus());
  }

  @Test
  void shouldThrowIfEmailIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      ClienteBO bo = new ClienteBO(1l, "Test", null, "00000000000", "000000000");
      bo.validate();
    });
  }

  @Test
  void shouldThrowIfCnpjIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClienteBO(1l, "Test", "test@email.com", null, "000000000");
    });
  }

  @Test
  void shouldThrowIfNameIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClienteBO(1l, null, "test@email.com", "00000000000", "000000000");
    });
  }

  @Test
  void shouldThrowIfTelefoneIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClienteBO(1l, "Test", "test@email.com", "00000000000", null);
    });
  }
}

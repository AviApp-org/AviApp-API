package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import br.com.aviapp.api.domain.errors.InvalidParamError;

public class ClientBOTest {

  private ClientBO makeClientBO() {
    ClientBO bo = new ClientBO(1L, "Test", "teste@teste.com", "00000000000", "000000000", LocalDate.now());

    return bo;
  }

  @Test
  void shouldInactivateAClient() {
    ClientBO bo = this.makeClientBO();
    bo.deactivate();

    assertEquals(ClientStatusEnum.INACTIVE, bo.getStatus());
  }

  @Test
  void shouldActivateAClient() {
    ClientBO bo = this.makeClientBO();
    bo.activate();

    assertEquals(ClientStatusEnum.ACTIVE, bo.getStatus());
  }

  @Test
  void shouldThrowIfEmailIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "Test", null, "00000000000", "000000000", LocalDate.now());
    });
  }

  @Test
  void shouldThrowIfCpfIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "Test", "test@email.com", null, "000000000", LocalDate.now());
    });
  }

  @Test
  void shouldThrowIfNameIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, null, "test@email.com", "00000000000", "000000000", LocalDate.now());
    });
  }

  @Test
  void shouldThrowIfTelefoneIsNull() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "Test", "test@email.com", "00000000000", null, LocalDate.now());
    });
  }

  @Test
  void shouldThrowIfParamIsBlank() {
    assertThrows(InvalidParamError.class, () -> {
      new ClientBO(1l, "", "test@email.com", "00000000000", "000000000", LocalDate.now());
    });
  }

  @Test
  void shouldInitializeClientAsActive() {
    ClientBO bo = this.makeClientBO();

    assertEquals(ClientStatusEnum.ACTIVE, bo.getStatus());
  }

  @Test
  @Description("Should not throw if client is older than 18")
  void shouldNotThrowIfClientIsOlderThan18() {
    ClientBO bo = this.makeClientBO();
    bo.setBirthDate(LocalDate.now().minusYears(20));

    assertDoesNotThrow(() -> bo.isBirthDateValid());
  }

  @Test
  @Description("Should throw if client is not older than 18")
  void shouldThrowIfClientIsNotOlderThan18() {
    ClientBO bo = this.makeClientBO();

    assertThrows(RuntimeException.class, () -> bo.isBirthDateValid());
  }
}

package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EnderecoBOTest {
  @Test
  void shouldInstanciateWithCorrectValues() {
    EnderecoBO sut = new EnderecoBO(null, "Rua Xavantes", "85806020", "Santa Cruz", "Cascavel", "Paraná");

    assertAll("Match values",
        () -> assertEquals("Rua Xavantes", sut.getLogradouro()),
        () -> assertEquals("85806020", sut.getCep()),
        () -> assertEquals("Santa Cruz", sut.getBairro()),
        () -> assertEquals("Cascavel", sut.getCidade()),
        () -> assertEquals("Paraná", sut.getEstado()));
  }

  @Test
  void shouldRemoveBlankSpaces() {
    EnderecoBO sut = new EnderecoBO(null, "Rua Xavantes      ", "     85806020", "Santa Cruz", "Cascavel", "Paraná");

    assertAll("Match values",
        () -> assertEquals("Rua Xavantes", sut.getLogradouro()),
        () -> assertEquals("85806020", sut.getCep()),
        () -> assertEquals("Santa Cruz", sut.getBairro()),
        () -> assertEquals("Cascavel", sut.getCidade()),
        () -> assertEquals("Paraná", sut.getEstado()));
  }
}

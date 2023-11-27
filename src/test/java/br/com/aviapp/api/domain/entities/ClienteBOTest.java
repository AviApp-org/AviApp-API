package br.com.aviapp.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;

public class ClienteBOTest {
  
  @Test
  void shouldInactivateAClient() {
    ClienteBO bo = new ClienteBO(1l, "Test", "teste@teste.com", "00000000000", "000000000");
    bo.desativar();

    assertEquals(EnumStatusCliente.INATIVO, bo.getStatus());
  }

  @Test
  void shouldActivateAClient() {
    ClienteBO bo = new ClienteBO(1l, "Test", "teste@teste.com", "00000000000", "000000000", EnumStatusCliente.INATIVO);
    bo.ativar();

    assertEquals(EnumStatusCliente.ATIVO, bo.getStatus());
  }
}

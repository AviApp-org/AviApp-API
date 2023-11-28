package br.com.aviapp.api.infra.jakarta.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.infra.jakarta.models.JakartaCliente;

public class JakartaClienteMapperTest {
  
  @Test
  void shouldMapToEntity() {
    ClienteBO bo = new ClienteBO(1l, "Test", "teste@teste.com", "00000000000", "000000000");
    JakartaCliente entity = JakartaClienteMapper.toEntity(bo);

    assertEquals(bo.getCnpj(), entity.getCnpj());
    assertEquals(bo.getEmail(), entity.getEmail());
    assertEquals(bo.getTelefone(), entity.getTelefone());
    assertEquals(bo.getNome(), entity.getNome());
  }
}

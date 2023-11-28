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

  @Test
  void shouldMapToDomain() {
    JakartaCliente entity = new JakartaCliente();

    entity.setId(1l);
    entity.setNome("Test");
    entity.setEmail("test@test.com");
    entity.setCnpj("00000000000");
    entity.setTelefone("000000000");

    ClienteBO bo = JakartaClienteMapper.toDomain(entity);

    assertEquals(entity.getCnpj(), bo.getCnpj());
    assertEquals(entity.getEmail(), bo.getEmail());
    assertEquals(entity.getTelefone(), bo.getTelefone());
    assertEquals(entity.getNome(), bo.getNome());
  }
}

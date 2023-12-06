package br.com.aviapp.api.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;

@ActiveProfiles("test")
public class ClienteMapperTest {

  @Test
  void shouldParseDtoToBoCorrectly() {
    ClienteDTO dto = new ClienteDTO(1l, "Test", "test@email.com", "00000000000", "000000000");
    ClienteBO bo = ClienteMapper.toBO(dto);

    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getNome(), bo.getNome());
    assertEquals(dto.getEmail(), bo.getEmail());
    assertEquals(dto.getCnpj(), bo.getCnpj());
    assertEquals(dto.getTelefone(), bo.getTelefone());
    assertEquals(dto.getStatus(), bo.getStatus());
  }

  @Test
  void shouldParseBoToDtoCorrectly() {
    ClienteBO bo = new ClienteBO(1l, "Test", "test@email.com", "00000000000", "000000000");
    ClienteDTO dto = ClienteMapper.toDTO(bo);

    assertEquals(bo.getId(), dto.getId());
    assertEquals(bo.getNome(), dto.getNome());
    assertEquals(bo.getEmail(), dto.getEmail());
    assertEquals(bo.getCnpj(), dto.getCnpj());
    assertEquals(bo.getTelefone(), dto.getTelefone());
    assertEquals(bo.getStatus(), dto.getStatus());
  }

}

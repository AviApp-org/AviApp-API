package br.com.aviapp.api.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;

@ActiveProfiles("test")
public class ClienteMapperTest {

  @Test
  void shouldParseDtoToBoCorrectly() {
    ClientDTO dto = new ClientDTO(1l, "Test", "test@email.com", "00000000000", "000000000");
    ClienteBO bo = ClienteMapper.toBO(dto);

    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getName(), bo.getName());
    assertEquals(dto.getEmail(), bo.getEmail());
    assertEquals(dto.getCpf(), bo.getCpf());
    assertEquals(dto.getTelefone(), bo.getTelefone());
    assertEquals(dto.getStatus(), bo.getStatus());
  }

  @Test
  void shouldParseBoToDtoCorrectly() {
    ClienteBO bo = new ClienteBO(1l, "Test", "test@email.com", "00000000000", "000000000");
    ClientDTO dto = ClienteMapper.toDTO(bo);

    assertEquals(bo.getId(), dto.getId());
    assertEquals(bo.getName(), dto.getName());
    assertEquals(bo.getEmail(), dto.getEmail());
    assertEquals(bo.getCpf(), dto.getCpf());
    assertEquals(bo.getTelefone(), dto.getTelefone());
    assertEquals(bo.getStatus(), dto.getStatus());
  }

}

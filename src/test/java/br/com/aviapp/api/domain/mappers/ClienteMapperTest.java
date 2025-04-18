package br.com.aviapp.api.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.domain.entities.ClientBO;

@ActiveProfiles("test")
public class ClienteMapperTest {
/* 
  @Test
  void shouldParseDtoToBoCorrectly() {
    ClientDTO dto = new ClientDTO(1l, "Test", "test@email.com", "00000000000", "000000000");
    ClientBO bo = ClientMapperBO.toBO(dto);

    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getName(), bo.getName());
    assertEquals(dto.getEmail(), bo.getEmail());
    assertEquals(dto.getCnpj(), bo.getCnpj());
    assertEquals(dto.getPhone(), bo.getPhone());
    assertEquals(dto.getStatus(), bo.getStatus());
  }

  @Test
  void shouldParseBoToDtoCorrectly() {
    ClientBO bo = new ClientBO(1l, "Test", "test@email.com", "00000000000", "000000000");
    ClientDTO dto = ClientMapperBO.toDTO(bo);

    assertEquals(bo.getId(), dto.getId());
    assertEquals(bo.getName(), dto.getName());
    assertEquals(bo.getEmail(), dto.getEmail());
    assertEquals(bo.getCnpj(), dto.getCnpj());
    assertEquals(bo.getPhone(), dto.getPhone());
    assertEquals(bo.getStatus(), dto.getStatus());
  }
*/
}

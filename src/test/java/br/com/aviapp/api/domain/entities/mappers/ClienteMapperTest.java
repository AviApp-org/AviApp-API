package br.com.aviapp.api.domain.entities.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.domain.mappers.ClienteMapper;

public class ClienteMapperTest {

  @Test
  void shouldParseDtoToBoCorrectly() {
    ClienteDTO dto = new ClienteDTO(1l, "Test", "test@email.com", "00000000000", "000000000", EnumStatusCliente.ATIVO);
    ClienteBO bo = ClienteMapper.toBO(dto);

    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getNome(), bo.getNome());
    assertEquals(dto.getEmail(), bo.getEmail());
    assertEquals(dto.getCnpj(), bo.getCnpj());
    assertEquals(dto.getTelefone(), bo.getTelefone());
    assertEquals(dto.getStatus(), bo.getStatus());
  }
}

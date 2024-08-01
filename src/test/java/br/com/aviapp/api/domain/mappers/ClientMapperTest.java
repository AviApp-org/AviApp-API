package br.com.aviapp.api.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;
import br.com.aviapp.api.presentation.dto.request.client.CreateClientRequestDTO;

@ActiveProfiles("test")
public class ClientMapperTest {

  @Test
  void shouldParseDtoToBoCorrectly() {
    ClientDTO dto = new ClientDTO(1l, "Test", "test@email.com", "00000000000", "000000000",
        LocalDate.now().minusYears(20), ClientStatusEnum.ACTIVE);
    ClientBO bo = ClientMapper.toBO(dto);

    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getName(), bo.getName());
    assertEquals(dto.getEmail(), bo.getEmail());
    assertEquals(dto.getCpf(), bo.getCpf());
    assertEquals(dto.getPhone(), bo.getPhone());
    assertEquals(dto.getStatus(), bo.getStatus());
  }

  @Test
  void shouldParseBoToDtoCorrectly() {
    ClientBO bo = new ClientBO(1l, "Test", "test@email.com", "00000000000", "000000000",
        LocalDate.now().minusYears(20));
    ClientDTO dto = ClientMapper.toDTO(bo);

    assertEquals(bo.getId(), dto.getId());
    assertEquals(bo.getName(), dto.getName());
    assertEquals(bo.getEmail(), dto.getEmail());
    assertEquals(bo.getCpf(), dto.getCpf());
    assertEquals(bo.getPhone(), dto.getPhone());
    assertEquals(bo.getStatus(), dto.getStatus());
  }

  @Test
  void shouldMapCreateClientRequestDTOToClientBOCorrectly() {
    CreateClientRequestDTO dto = new CreateClientRequestDTO(null, "Test", "test@email.com", "00000000000", "000000000",
        LocalDate.now().minusYears(20), ClientStatusEnum.ACTIVE);
    ClientBO bo = ClientMapper.toBO(dto);

    assertAll(() -> {
      assertEquals(dto.getId(), bo.getId());
      assertEquals(dto.getName(), bo.getName());
      assertEquals(dto.getEmail(), bo.getEmail());
      assertEquals(dto.getCpf(), bo.getCpf());
      assertEquals(dto.getPhone(), bo.getPhone());
      assertEquals(dto.getStatus(), bo.getStatus());
    });
  }

  @Test
  void shouldMapFromPgSqlEntityToBO() {
    PgSqlClientEntity entity = new PgSqlClientEntity(1L, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", LocalDate.now().minusYears(20), ClientStatusEnum.ACTIVE);

    ClientBO bo = ClientMapper.toBO(entity);

    assertAll(() -> {
      assertEquals(entity.getId(), bo.getId());
      assertEquals(entity.getName(), bo.getName());
      assertEquals(entity.getEmail(), bo.getEmail());
      assertEquals(entity.getCpf(), bo.getCpf());
      assertEquals(entity.getPhone(), bo.getPhone());
      assertEquals(entity.getStatus(), bo.getStatus());
      assertEquals(entity.getBirthDate(), bo.getBirthDate());
    });
  }

}

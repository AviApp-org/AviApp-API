package br.com.aviapp.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;
import br.com.aviapp.api.infra.postgresql.repository.PgSqlClientRepository;
import br.com.aviapp.api.presentation.dto.request.client.CreateClientRequestDTO;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

  @Mock
  private PgSqlClientRepository repository;

  private ClientService sut;

  @BeforeEach
  void setUp() {
    this.sut = new ClientService(repository);
    this.repository.deleteAll();
  }

  @Test
  void shouldCreateClientFromRequest() {
    CreateClientRequestDTO dto = new CreateClientRequestDTO(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", LocalDate.now().minusYears(20));
    PgSqlClientEntity entity = new PgSqlClientEntity(1L, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", LocalDate.now().minusYears(20), ClientStatusEnum.ACTIVE);

    Mockito.when(this.repository.save(Mockito.any(PgSqlClientEntity.class))).thenReturn(entity);
    ClientBO bo = this.sut.save(dto);

    assertNotNull(bo);
    assertEquals(entity, bo);
  }
}

package br.com.aviapp.api.infra.postgresql.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
public class PgSqlClientRepositoryTest {

  @Autowired
  private PgSqlClientRepository sut;

  @Test
  void shouldPersistClientOnDatabase() {
    PgSqlClientEntity entity = new PgSqlClientEntity(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", ClientStatusEnum.ACTIVE);

    PgSqlClientEntity persisted = sut.save(entity);

    assertNotNull(persisted.getId());
    assertEquals(entity, persisted);
  }
}

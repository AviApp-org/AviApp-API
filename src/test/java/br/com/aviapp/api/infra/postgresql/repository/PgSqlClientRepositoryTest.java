package br.com.aviapp.api.infra.postgresql.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
public class PgSqlClientRepositoryTest {

  @Autowired
  private PgSqlClientRepository sut;

  @BeforeEach
  void init() {
    sut.deleteAll();
  }

  @Test
  void shouldPersistClientOnDatabase() {
    PgSqlClientEntity entity = new PgSqlClientEntity(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", ClientStatusEnum.ACTIVE);

    PgSqlClientEntity persisted = sut.save(entity);

    assertNotNull(persisted.getId());
    assertEquals(entity, persisted);
  }

  @Test
  void shouldFindAllClients() {
    PgSqlClientEntity entity1 = new PgSqlClientEntity(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", ClientStatusEnum.ACTIVE);
    PgSqlClientEntity entity2 = new PgSqlClientEntity(null, "Bradley Cooper", "98765432109", "bradley@cooper.com", "(926) 321-6543", ClientStatusEnum.ACTIVE);

    sut.saveAll(List.of(entity1, entity2));

    List<PgSqlClientEntity> allClients = sut.findAll();

    assertEquals(2, allClients.size());
    assertTrue(allClients.contains(entity1));
    assertTrue(allClients.contains(entity2));
    assertNotEquals(entity1, entity2);
  }

  @Test
  void shouldFindClientById() {
    PgSqlClientEntity entity = new PgSqlClientEntity(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", ClientStatusEnum.ACTIVE);

    PgSqlClientEntity persisted = sut.save(entity);

    PgSqlClientEntity foundClient = sut.findById(persisted.getId()).orElse(null);

    assertEquals(persisted, foundClient);
    assertNotNull(foundClient);
  }

  @Test
  void shouldFindClientByCpf() {
    PgSqlClientEntity entity = new PgSqlClientEntity(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", ClientStatusEnum.ACTIVE);

    PgSqlClientEntity persisted = sut.save(entity);

    PgSqlClientEntity foundClient = sut.findByCpf("12345678901");

    assertEquals(persisted, foundClient);
    assertNotNull(foundClient);
  }

  @Test
  @Description("Should return null if no entity is found")
  void shouldFindClientByCpf_2() {
    PgSqlClientEntity entity = new PgSqlClientEntity(null, "Seth Austin", "12345678901", "nuwha@duzgibe.es", "(926) 432-4013", ClientStatusEnum.ACTIVE);

    sut.save(entity);

    PgSqlClientEntity foundClient = sut.findByCpf("000");

    assertNull(foundClient);
  }

}

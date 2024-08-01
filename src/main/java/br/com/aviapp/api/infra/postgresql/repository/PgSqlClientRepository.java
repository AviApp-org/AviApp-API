package br.com.aviapp.api.infra.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.aviapp.api.domain.repositories.IClientRepository;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;

public interface PgSqlClientRepository extends IClientRepository<PgSqlClientEntity>, JpaRepository<PgSqlClientEntity, Long>{

  @Override
  @Query("SELECT c From PgSqlClientEntity c WHERE c.cpf = ?1")
  public PgSqlClientEntity findByCpf(String cpf);
}

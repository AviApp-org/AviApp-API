package br.com.aviapp.api.infra.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;

public interface PgSqlClientRepository extends JpaRepository<PgSqlClientEntity, Long>{

}

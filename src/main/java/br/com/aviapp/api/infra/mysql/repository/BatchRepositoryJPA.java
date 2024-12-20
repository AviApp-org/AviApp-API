package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;

@Repository
public interface BatchRepositoryJPA extends JpaRepository<MySqlBatchEntity, Long> {

}
package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;

@Repository
public interface ClientRepositoryJPA extends JpaRepository<MySqlClientEntity, Long> {



}

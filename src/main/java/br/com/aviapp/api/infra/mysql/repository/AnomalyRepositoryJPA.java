package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aviapp.api.infra.mysql.models.MySqlAnomalyEntity;

public interface AnomalyRepositoryJPA extends JpaRepository<MySqlAnomalyEntity, Long> {
    
}

package br.com.aviapp.api.infra.mysql.repository;


import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aviapp.api.infra.mysql.models.MySqlAnomalyEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnomalyRepositoryJPA extends JpaRepository<MySqlAnomalyEntity, Long> {
    @Query("SELECT a FROM MySqlAnomalyEntity a WHERE a.aviary.id= :aviaryId")
    List<MySqlAnomalyEntity> findByAviaryId(Long aviaryId);
}

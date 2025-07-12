package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;


import java.util.Optional;

@Repository
public interface FarmRepositoryJPA extends JpaRepository<MySqlFarmEntity, Long> {

    @Query("SELECT f FROM MySqlFarmEntity f WHERE f.clientId.id= :clientId")
    Optional<MySqlFarmEntity> findByClient(Long clientId);
}

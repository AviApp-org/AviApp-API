package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlEggValueEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EggValueRepositoryJPA extends JpaRepository<MySqlEggValueEntity, Long> {

    @Query(value = "SELECT * FROM egg_value WHERE batch_id = :batchId ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
    Optional<MySqlEggValueEntity> findLastInsertedEggValue(@Param("batchId") Long batchId);

}

package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;

import java.util.List;

@Repository
public interface BatchRepositoryJPA extends JpaRepository<MySqlBatchEntity, Long> {

    List<MySqlBatchEntity> findByFarmId(MySqlFarmEntity farmId);

    @Query("SELECT c FROM MySqlBatchEntity c where c.farmId.id = :farmId AND status = ACTIVE")
    List<MySqlBatchEntity> listActiveBatchesByFarm (Long farmId);
}

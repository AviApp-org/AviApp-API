package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;

import java.util.List;

@Repository
public interface CollectChickenDataRepositoryJPA extends JpaRepository<MySqlCollectChickenDataEntity, Long> {
    @Query("SELECT c FROM MySqlCollectChickenDataEntity c WHERE c.collect.aviary.id = :aviaryId")
    List<MySqlCollectChickenDataEntity> findChickenCollectsByAviaryId(Long aviaryId);

    @Query("SELECT c FROM MySqlCollectChickenDataEntity c WHERE c.collect.employee.id = :employeeId")
    List<MySqlCollectChickenDataEntity> findChickenCollectsByEmployeeId(Long employeeId);
}

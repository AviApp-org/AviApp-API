package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;

import java.util.List;

@Repository
public interface CollectEggDataRepositoryJPA extends JpaRepository<MySqlCollectEggDataEntity, Long> {
    @Query("SELECT c FROM MySqlCollectEggDataEntity c WHERE c.collect.aviary.id = :aviaryId")
    List<MySqlCollectEggDataEntity> findEggCollectsByAviaryId(Long aviaryId);

    @Query("SELECT c FROM MySqlCollectEggDataEntity c WHERE c.collect.employee.id = :employeeId")
    List<MySqlCollectEggDataEntity> findEggCollectsByEmployeeId(Long employeeId);
}

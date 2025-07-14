package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepositoryJPA extends JpaRepository<MySqlEmployeeEntity, Long> {
    @Query("SELECT e FROM MySqlEmployeeEntity e WHERE e.farmId.id= :farmId")
    List<MySqlEmployeeEntity> findByFarmId(Long farmId);
}

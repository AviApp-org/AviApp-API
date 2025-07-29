package br.com.aviapp.api.infra.mysql.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;

@Repository
public interface EntityLookupRepository {
    Optional<MySqlClientEntity> findClientById(Long id);

    Optional<MySqlEmployeeEntity> findEmployeeById(Long id);

    Optional<MySqlFarmEntity> findFarmById(Long id);

    Optional<MySqlBatchEntity> findBatchById(Long id);

    Optional<MySqlAviaryEntity> findAviaryById(Long id);

}

package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;

import java.util.Optional;

@Repository
public interface AddressRepositoryJPA extends JpaRepository <MySqlAddressEntity, Long> {

    // Alternativa mais simples
    @Query("SELECT f.address FROM MySqlFarmEntity f WHERE f.id = :farmId")
    Optional<MySqlAddressEntity> findAddressByFarmId(Long farmId);
}

package br.com.aviapp.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<MySqlClientEntity, Long> {

    
    
}

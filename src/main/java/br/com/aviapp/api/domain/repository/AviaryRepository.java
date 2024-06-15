package br.com.aviapp.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;

@Repository
public interface AviaryRepository extends JpaRepository <MySqlAviaryEntity, Long> {
    
}

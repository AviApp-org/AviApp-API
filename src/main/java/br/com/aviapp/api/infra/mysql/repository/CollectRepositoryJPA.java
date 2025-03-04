package br.com.aviapp.api.infra.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;

@Repository
public interface CollectRepositoryJPA extends JpaRepository<MySqlCollectEntity, Long> {

}

package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;

import java.util.List;

@Repository
public interface CollectChickenDataRepositoryJPA extends JpaRepository<MySqlCollectChickenDataEntity, Long> {

}

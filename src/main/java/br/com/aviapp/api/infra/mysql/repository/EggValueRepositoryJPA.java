package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlEggValueEntity;

@Repository
public interface EggValueRepositoryJPA extends JpaRepository<MySqlEggValueEntity, Long> {

    @Query("SELECT e FROM MySqlEggValueEntity e LAST_INSERTED_ID()")
    MySqlEggValueEntity findLastInsertedEggValue();

}

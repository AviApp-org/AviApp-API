package br.com.aviapp.api.infra.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CollectChickenDataRepositoryJPA extends JpaRepository<MySqlCollectChickenDataEntity, Long> {
    @Query("SELECT c FROM MySqlCollectChickenDataEntity c WHERE c.aviary.id = :aviaryId")
    List<MySqlCollectChickenDataEntity> findByAviary(Long aviaryId);

    @Query("SELECT c FROM MySqlCollectChickenDataEntity c WHERE FUNCTION('DATE', c.collectionDate) = FUNCTION('DATE', :date)")
    List<MySqlCollectChickenDataEntity> findByCollectionDateIgnoringTime(LocalDate date);

    @Query("SELECT c FROM MySqlCollectChickenDataEntity c WHERE c.aviary.id = :aviaryId AND FUNCTION('DATE', c.collectionDate) = FUNCTION('DATE', :date)")
    List<MySqlCollectChickenDataEntity> findByAviaryAndCollectionDateIgnoringTime(Long aviaryId, LocalDate date);

}

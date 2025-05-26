package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CollectEggDataRepositoryJPA extends JpaRepository<MySqlCollectEggDataEntity, Long> {

    @Query("SELECT c FROM MySqlCollectEggDataEntity c WHERE c.aviary.id= :aviaryId")
    List<MySqlCollectEggDataEntity> findByAviary(Long aviaryId);

    @Query("SELECT c FROM MySqlCollectEggDataEntity c WHERE FUNCTION('DATE', c.collectionDate) = FUNCTION('DATE', :date)")
    List<MySqlCollectEggDataEntity> findByCollectionDateIgnoringTime(LocalDate date);

    @Query("SELECT c FROM MySqlCollectEggDataEntity c WHERE c.aviary.id = :aviaryId AND FUNCTION('DATE', c.collectionDate) = FUNCTION('DATE', :date)")
    List<MySqlCollectEggDataEntity> findByAviaryAndCollectionDateIgnoringTime(Long aviaryId, LocalDate date);

}

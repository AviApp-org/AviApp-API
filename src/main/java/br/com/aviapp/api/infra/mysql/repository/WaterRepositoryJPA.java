package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.infra.mysql.models.MySqlWaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WaterRepositoryJPA extends JpaRepository<MySqlWaterEntity, Long > {

    @Query("SELECT c FROM MySqlWaterEntity c WHERE c.aviaryId.id= :aviaryId")
    List<MySqlWaterEntity> findByAviary(Long aviaryId);

    @Query("SELECT c FROM MySqlWaterEntity c WHERE FUNCTION('DATE', c.collectionDate) = FUNCTION('DATE', :date)")
    List<MySqlWaterEntity> findByCollectionDateIgnoringTime(LocalDate date);

    @Query("SELECT c FROM MySqlWaterEntity c WHERE c.aviaryId.id = :aviaryId AND FUNCTION('DATE', c.collectionDate) = FUNCTION('DATE', :date)")
    List<MySqlWaterEntity> findByAviaryAndCollectionDateIgnoringTime(Long aviaryId, LocalDate date);
}

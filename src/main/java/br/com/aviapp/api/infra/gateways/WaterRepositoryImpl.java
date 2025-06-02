package br.com.aviapp.api.infra.gateways;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.application.gateways.WaterRepository;
import br.com.aviapp.api.infra.mappers.WaterMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlWaterEntity;
import br.com.aviapp.api.infra.mysql.repository.WaterRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class WaterRepositoryImpl implements WaterRepository {

    private final WaterRepositoryJPA repositoryJPA;
    private final WaterMapperEntity mapperEntity;

    public WaterRepositoryImpl(WaterRepositoryJPA repositoryJPA, WaterMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
    }

    @Override
    public WaterDTO createWaterReport(WaterDTO waterDTO) {
        MySqlWaterEntity waterEntity = mapperEntity.toEntity(waterDTO);
        MySqlWaterEntity waterEntitySaved = repositoryJPA.save(waterEntity);

        return mapperEntity.toDTO(waterEntitySaved);

    }

    @Override
    public List<WaterDTO> listWaterRecordByAviary(Long aviaryId) {
        List<MySqlWaterEntity> waterEntities = repositoryJPA.findByAviary(aviaryId);
        return waterEntities.stream().map(mapperEntity::toDTO).toList();
    }

    @Override
    public List<WaterDTO> getAllWaterRecords() {
        return repositoryJPA.findAll().stream().map(mapperEntity::toDTO).toList();
    }

    @Override
    public List<WaterDTO> getWaterRecordByDate(LocalDate date) {
        List<MySqlWaterEntity> waterEntities = repositoryJPA.findByCollectionDateIgnoringTime(date);
        return waterEntities.stream().map(mapperEntity::toDTO).toList();
    }

    @Override
    public List<WaterDTO> getWaterRecordByAviaryAndDate(Long aviaryId, LocalDate date) {
      List<MySqlWaterEntity> waterEntities = repositoryJPA.findByAviaryAndCollectionDateIgnoringTime(aviaryId, date);
        return waterEntities.stream().map(mapperEntity::toDTO).toList();
    }

    @Override
    public void deleteWaterRecord(Long id) {
        repositoryJPA.deleteById(id);
    }

    @Override
    public Optional<WaterDTO> getWaterRecordById(Long id) {
        return repositoryJPA.findById(id).map(mapperEntity::toDTO);
    }
}

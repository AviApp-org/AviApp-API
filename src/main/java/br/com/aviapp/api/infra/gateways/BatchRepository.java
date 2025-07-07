package br.com.aviapp.api.infra.gateways;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.infra.mappers.BatchMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.BatchRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BatchRepository implements IBatch {

    private final BatchRepositoryJPA repositoryJPA;
    private final BatchMapperEntity mapperEntity;
    private final EntityLookupRepository entityLookupRepository;

    public BatchRepository(BatchRepositoryJPA repositoryJPA, BatchMapperEntity mapperEntity, EntityLookupRepository entityLookupRepository) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;

        this.entityLookupRepository = entityLookupRepository;
    }

    @Override
    public BatchDTO createBatch(BatchDTO batchDTO) {
        MySqlBatchEntity entity = mapperEntity.toEntity(batchDTO);
        repositoryJPA.save(entity);

        return mapperEntity.toDTO(entity);
    }

    @Override
    public void deleteBatch(Long batchID) {
        repositoryJPA.deleteById(batchID);
    }

    @Override
    public Optional<BatchDTO> findBatch(Long batchID) {
        return repositoryJPA.findById(batchID).map(mapperEntity::toDTO);
    }

    @Override
    public List<BatchDTO> findBatchesByFarmId(Long farmId) {
        Optional<MySqlFarmEntity> farmEntity = entityLookupRepository.findFarmById(farmId);
        if (farmEntity.isEmpty()) {
            return List.of();
        }
        List<MySqlBatchEntity> entities = repositoryJPA.findByFarmId(farmEntity.get());
        return entities.stream()
                .map(mapperEntity::toDTO)
                .toList();
    }

    @Override
    public List<BatchDTO> listBatchesActiveByFarm(Long farmId) {

        return mapperEntity.toDTOList(repositoryJPA.listActiveBatchesByFarm(farmId));

    }

    @Override
    public Optional<BatchDTO> updateBatch(Long id, BatchDTO batchDTO) {
        return repositoryJPA.findById(id)
                .map(existingBatch -> {
                    if (batchDTO.name() != null) {
                        existingBatch.setName(batchDTO.name());
                    }
                    if (batchDTO.startDate() != null) {
                        existingBatch.setStartDate(batchDTO.startDate());
                    }

                    MySqlBatchEntity savedEntity = repositoryJPA.save(existingBatch);
                    return mapperEntity.toDTO(savedEntity);
                });
    }

    @Override
    public void save(BatchDTO batchDTO) {
        repositoryJPA.save(mapperEntity.toEntity(batchDTO));
    }
}

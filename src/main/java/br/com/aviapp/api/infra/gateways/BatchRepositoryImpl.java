package br.com.aviapp.api.infra.gateways;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.BatchRepository;
import br.com.aviapp.api.infra.mappers.BatchMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.repository.BatchRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BatchRepositoryImpl implements BatchRepository {
    private final BatchRepositoryJPA repositoryJPA;

    private final BatchMapperEntity mapperEntity;

    public BatchRepositoryImpl(BatchRepositoryJPA repositoryJPA, BatchMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
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
        return repositoryJPA.findById(batchID).map(mapperEntity ::toDTO);
    }

    @Override
    public List<BatchDTO> findBatchesByFarmId(Long farmId) {
        return List.of();
    }

    @Override
    public Optional<BatchDTO> updateBatch(Long id, BatchDTO batchDTO) {
        return Optional.empty();
    }
}

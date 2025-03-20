package br.com.aviapp.api.infra.mappers;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.domain.enums.EnumBatchStatus;
import br.com.aviapp.api.infra.mysql.enums.BatchStatusType;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class BatchMapperEntity {

    private final EntityLookupRepository repository;

    public BatchMapperEntity(EntityLookupRepository repository) {
        this.repository = repository;
    }

    public BatchDTO toDTO(MySqlBatchEntity entity) {
        EnumBatchStatus batchStatus = entity.getStatus() != null 
            ? EnumBatchStatus.valueOf(entity.getStatus().name()) 
            : null;
            
        return new BatchDTO(
                entity.getId(),
                entity.getName(),
                entity.getStartDate(),
                batchStatus,
                entity.getFarmId().getId()
        );
    }

    public MySqlBatchEntity toEntity(BatchDTO batchDTO) {
        MySqlFarmEntity farm = repository.findFarmById(batchDTO.farmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));
    
        MySqlBatchEntity batch = new MySqlBatchEntity();
    
        batch.setId(batchDTO.id());
        batch.setName(batchDTO.name());
        batch.setStartDate(batchDTO.startDate());
        
        // Fix: Actually set the status on the batch entity
        if (batchDTO.status() != null) {
            batch.setStatus(BatchStatusType.valueOf(batchDTO.status().name()));
        }
        
        batch.setFarmId(farm);
    
        return batch;
    }
    

    public List<BatchDTO> toDTOList(List<MySqlBatchEntity> batches) {
        return batches.stream().
                map(this::toDTO).
                collect(Collectors.toList());
    }

    public List<MySqlBatchEntity> toEntityList(List<BatchDTO> batches){
        return batches.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}

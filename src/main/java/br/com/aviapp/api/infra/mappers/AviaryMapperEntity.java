package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class AviaryMapperEntity {
    
    private final EntityLookupRepository repository;

    public AviaryMapperEntity(EntityLookupRepository repository) {
        this.repository = repository;
    }

    public AviaryDTO toDTO(MySqlAviaryEntity entity) {
        return new AviaryDTO(
            entity.getId(),
            entity.getName(),
            entity.getIntialTotalAmount(),
            entity.getInitialAmountOfRoosters(),
            entity.getInitialAmountOfChickens(),
            entity.getBatchId().getId()
        );
    }

    public MySqlAviaryEntity toEntity(AviaryDTO dto) {
        MySqlBatchEntity batch = repository.findBatchById(dto.batchId())
                .orElseThrow(() -> new EntityNotFoundException("Batch not found"));

        return new MySqlAviaryEntity(
            dto.id(),
            dto.name(),
            dto.intialTotalAmount(),
            dto.initialAmountOfRoosters(),
            dto.initialAmountOfChickens(),
            batch
        );
    }

    public List<AviaryDTO> toDTOList(List<MySqlAviaryEntity> entities) {
        return entities.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<MySqlAviaryEntity> toEntityList(List<AviaryDTO> dtos) {
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}

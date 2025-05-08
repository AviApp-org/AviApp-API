package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class CollectChickenMapperEntity {
    private final EntityLookupRepository lookupRepository;

    public CollectChickenMapperEntity(EntityLookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    public CollectChickenDTO toDTO(MySqlCollectChickenDataEntity entity) {
        return new CollectChickenDTO(
                entity.getId(),
                entity.getAviary().getId(),
                entity.getDeadRoosters(),
                entity.getDeadChickens(),
                entity.getObservation(),
                entity.getCollectionDate()
        );
    }

    public MySqlCollectChickenDataEntity toEntity(CollectChickenDTO dto) {
        MySqlAviaryEntity aviary = lookupRepository.findAviaryById(dto.aviaryId())
                .orElseThrow(() -> new EntityNotFoundException("Aviário não encontrado"));

        MySqlCollectChickenDataEntity entity = new MySqlCollectChickenDataEntity();
        entity.setId(dto.id());
        entity.setAviary(aviary);
        entity.setDeadRoosters(dto.deadRoosters());
        entity.setDeadChickens(dto.deadChickens());
        entity.setObservation(dto.observation());
        entity.setCollectionDate(dto.collectionDate());
        return entity;
    }

    public List<CollectChickenDTO> toDTOList(List<MySqlCollectChickenDataEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlCollectChickenDataEntity> toEntityList(List<CollectChickenDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

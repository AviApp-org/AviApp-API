package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.domain.enums.EnumChickenDeathCause;
import br.com.aviapp.api.infra.mysql.enums.ChickenDeathCause;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEntity;
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
                entity.getCollect().getId(),
                entity.getDeadRoosters(),
                entity.getDeadChickens(),
                entity.getTotalDeath(),
                EnumChickenDeathCause.valueOf(entity.getDeathCause().name()),
                entity.getObservation());
    }

    public MySqlCollectChickenDataEntity toEntity(CollectChickenDTO dto) {
        MySqlCollectEntity collect = lookupRepository.findCollectById(dto.collectId())
                .orElseThrow(() -> new EntityNotFoundException("Collect not found"));

        MySqlCollectChickenDataEntity entity = new MySqlCollectChickenDataEntity();
        entity.setId(dto.id());
        entity.setCollect(collect);
        entity.setDeadRoosters(dto.deadRoosters());
        entity.setDeadChickens(dto.deadChickens());
        entity.setTotalDeath(dto.totalDeath());
        entity.setDeathCause(ChickenDeathCause.valueOf(dto.deathCause().name()));
        entity.setObservation(dto.observation());

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

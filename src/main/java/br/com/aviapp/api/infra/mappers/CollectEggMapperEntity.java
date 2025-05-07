package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.infra.mysql.enums.EggType;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class CollectEggMapperEntity {
    
    private final EntityLookupRepository repository;

    public CollectEggMapperEntity(EntityLookupRepository repository) {
        this.repository = repository;
    }

    public CollectEggDataDTO toDTO(MySqlCollectEggDataEntity entity) {
        return new CollectEggDataDTO(
            entity.getId(),
            entity.getAviary().getId()
//            EnumEggType.valueOf(entity.getEgg().name()),
//            entity.getQuantity()
        );
    }

    public MySqlCollectEggDataEntity toEntity(CollectEggDataDTO dto) {
        MySqlAviaryEntity aviary = repository.findAviaryById(dto.aviaryId())
                .orElseThrow(() -> new EntityNotFoundException("Collect not found"));

        MySqlCollectEggDataEntity entity = new MySqlCollectEggDataEntity();
        entity.setId(dto.id());
        entity.setAviary(aviary);
//        entity.setEgg(EggType.valueOf(dto.egg().name()));
//        entity.getEggDetails().setQuantity(dto.quantity());
        
        return entity;
    }

    public List<CollectEggDataDTO> toDTOList(List<MySqlCollectEggDataEntity> entities) {
        return entities.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<MySqlCollectEggDataEntity> toEntityList(List<CollectEggDataDTO> dtos) {
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}

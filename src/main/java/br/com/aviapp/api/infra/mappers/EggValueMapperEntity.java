package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.infra.mysql.models.MySqlEggValueEntity;
import br.com.aviapp.api.infra.mysql.enums.EggType;

public class EggValueMapperEntity {
    
    public EggValueDTO toDTO(MySqlEggValueEntity entity) {
        return new EggValueDTO(
            entity.getId(),
            EnumEggType.valueOf(entity.getEgg().name()),
            entity.getTimestamp(),
            entity.getValue()
        );
    }

    public MySqlEggValueEntity toEntity(EggValueDTO dto) {
        MySqlEggValueEntity entity = new MySqlEggValueEntity();
        entity.setId(dto.eggId());
        entity.setEgg(EggType.valueOf(dto.egg().name()));
        entity.setTimestamp(dto.timeStamp());
        entity.setValue(dto.value());
        return entity;
    }

    public List<EggValueDTO> toDTOList(List<MySqlEggValueEntity> entities) {
        return entities.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<MySqlEggValueEntity> toEntityList(List<EggValueDTO> dtos) {
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}

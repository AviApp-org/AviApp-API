package br.com.aviapp.api.infra.mappers;

import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.infra.mysql.enums.EggType;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EggDetailsMapperEntity {

    public EggDetailDTO toDTO(MySqlEggDetailEntity entity) {
        return new EggDetailDTO(
                EnumEggType.valueOf(entity.getType().name()),
                entity.getQuantity()
        );
    }

    public MySqlEggDetailEntity toEntity(EggDetailDTO dto) {
        MySqlEggDetailEntity entity = new MySqlEggDetailEntity();
        entity.setType(EggType.valueOf(dto.type().name()));
        entity.setQuantity(dto.quantity());
        return entity;
    }

    public List<EggDetailDTO> toDTOList(List<MySqlEggDetailEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlEggDetailEntity> toEntityList(List<EggDetailDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


}

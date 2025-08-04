package br.com.aviapp.api.infra.mappers;

import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.infra.mysql.enums.EggType;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EggDetailsMapperEntity {

    public EggDetailDTO toDTO(MySqlEggDetailEntity entity) {
        if (entity == null) {
            return null;
        }

        try {
            return new EggDetailDTO(
                    EnumEggType.valueOf(entity.getType().name()),
                    entity.getQuantity()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Tipo de ovo inválido: " + entity.getType().name());
        }
    }

    public MySqlEggDetailEntity toEntity(EggDetailDTO dto) {
        if (dto == null) {
            return null;
        }

        MySqlEggDetailEntity entity = new MySqlEggDetailEntity();
        try {
            entity.setType(EggType.valueOf(dto.type().name()));
            entity.setQuantity(dto.quantity());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Tipo de ovo inválido: " + dto.type().name());
        }
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

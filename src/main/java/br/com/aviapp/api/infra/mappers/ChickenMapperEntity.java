package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlChickenEntity;

public class ChickenMapperEntity {

    public ChickenDTO toDTO(MySqlChickenEntity entity) {
        return new ChickenDTO(
                entity.getId(),
                entity.getCurrentRoosters(),
                entity.getCurrentChickens()
                );
    }

    public MySqlChickenEntity toEntity(ChickenDTO dto) {
        return new MySqlChickenEntity(
                dto.id(),
                dto.currentRoosters(),
                dto.currentChickens()
                );
    }

    public List<ChickenDTO> toDTOList(List<MySqlChickenEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlChickenEntity> toEntityList(List<ChickenDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

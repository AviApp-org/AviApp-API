package br.com.aviapp.api.infra.mappers;

import br.com.aviapp.api.application.dto.WaterDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlWaterEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class WaterMapperEntity {

    private final EntityLookupRepository entityLookupRepository;

    public WaterMapperEntity(EntityLookupRepository entityLookupRepository) {
        this.entityLookupRepository = entityLookupRepository;
    }

    public WaterDTO toDTO(MySqlWaterEntity entity) {

        MySqlAviaryEntity aviary = entityLookupRepository.findAviaryById(entity.getAviaryId().getId()).
                orElseThrow(() -> new EntityNotFoundException("Aviário não encontrado"));

        return new WaterDTO(
                entity.getId(),
                aviary.getId(),
                entity.getVolume(),
                entity.getCollectionDate()
        );

    }

    public MySqlWaterEntity toEntity(WaterDTO waterDTO) {

        MySqlAviaryEntity aviary = entityLookupRepository.findAviaryById(waterDTO.aviaryId()).
                orElseThrow(() -> new EntityNotFoundException("Aviário não encontrado"));

        return new MySqlWaterEntity(
                waterDTO.id(),
                aviary,
                waterDTO.volume(),
                waterDTO.collectionDate()
        );
    }

    public List<WaterDTO> toDTOList(List<MySqlWaterEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlWaterEntity> toEntityList(List<WaterDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

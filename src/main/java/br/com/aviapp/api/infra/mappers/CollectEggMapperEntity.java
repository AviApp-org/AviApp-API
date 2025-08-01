package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class CollectEggMapperEntity {

    private final EntityLookupRepository repository;
    private final EggDetailsMapperEntity eggDetailsMapper;

    public CollectEggMapperEntity(EntityLookupRepository repository, EggDetailsMapperEntity eggDetailsMapper) {
        this.repository = repository;
        this.eggDetailsMapper = eggDetailsMapper;
    }

    public CollectEggDataDTO toDTO(MySqlCollectEggDataEntity entity) {

        List<EggDetailDTO> eggDetailDTOs = eggDetailsMapper.toDTOList(entity.getEggDetails());

        return new CollectEggDataDTO(
                entity.getId(),
                entity.getAviary().getId(),
                eggDetailDTOs,
                entity.getCollectionDate(),
                0,
                0,
                0,
                0

        );
    }

    public MySqlCollectEggDataEntity toEntity(CollectEggDataDTO dto) {
        MySqlAviaryEntity aviary = repository.findAviaryById(dto.aviaryId())
                .orElseThrow(() -> new EntityNotFoundException("Aviário não encontrado."));

        MySqlCollectEggDataEntity entity = new MySqlCollectEggDataEntity();
        entity.setId(dto.id());
        entity.setAviary(aviary);
        entity.setCollectionDate(dto.collectionDate());

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

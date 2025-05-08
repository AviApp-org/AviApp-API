package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.infra.mysql.enums.EggType;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class CollectEggMapperEntity {

    private final EntityLookupRepository repository;
    private final EggDetailMapperEntity eggDetailMapper;

    public CollectEggMapperEntity(EntityLookupRepository repository) {
        this.repository = repository;
        this.eggDetailMapper = new EggDetailMapperEntity();
    }

    public CollectEggDataDTO toDTO(MySqlCollectEggDataEntity entity) {
        List<EggDetailBO> eggDetails = eggDetailMapper.toBOList(entity.getEggDetails());

        return new CollectEggDataDTO(
                entity.getId(),
                entity.getAviary().getId(),
                eggDetails,
                entity.getCollectionDate()
        );
    }

    public MySqlCollectEggDataEntity toEntity(CollectEggDataDTO dto) {
        MySqlAviaryEntity aviary = repository.findAviaryById(dto.aviaryId())
                .orElseThrow(() -> new EntityNotFoundException("Aviary not found"));

        MySqlCollectEggDataEntity entity = new MySqlCollectEggDataEntity();
        entity.setId(dto.id());
        entity.setAviary(aviary);
        entity.setCollectionDate(dto.collectionDate());
        // Save the entity first to get an ID
        // Then set the egg details
        // Note: This might need to be handled in the service layer

        return entity;
    }

    public void setEggDetailsToEntity(MySqlCollectEggDataEntity entity, List<EggDetailBO> eggDetails) {
        List<MySqlEggDetailEntity> eggDetailEntities = eggDetailMapper.toEntityList(eggDetails, entity);
        entity.setEggDetails(eggDetailEntities);
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

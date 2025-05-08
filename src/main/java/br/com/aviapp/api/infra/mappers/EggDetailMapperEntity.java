package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.enums.EnumEggType;
import br.com.aviapp.api.domain.errors.InvalidParamError;
import br.com.aviapp.api.infra.mysql.enums.EggType;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;

public class EggDetailMapperEntity {
    public EggDetailBO toBO(MySqlEggDetailEntity entity) {
        try {
            return new EggDetailBO(
                    EnumEggType.valueOf(entity.getType().name()),
                    entity.getQuantity()
            );
        } catch (InvalidParamError e) {
            // Handle the exception or rethrow as a runtime exception
            throw new RuntimeException("Error mapping egg detail entity to BO", e);
        }
    }

    public MySqlEggDetailEntity toEntity(EggDetailBO bo, MySqlCollectEggDataEntity eggCollection) {
        MySqlEggDetailEntity entity = new MySqlEggDetailEntity();
        entity.setType(EggType.valueOf(bo.getType().name()));
        entity.setQuantity(bo.getQuantity());
        entity.setEggCollection(eggCollection);
        return entity;
    }

    public List<EggDetailBO> toBOList(List<MySqlEggDetailEntity> entities) {
        return entities.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<MySqlEggDetailEntity> toEntityList(List<EggDetailBO> bos, MySqlCollectEggDataEntity eggCollection) {
        return bos.stream()
                .map(bo -> toEntity(bo, eggCollection))
                .collect(Collectors.toList());
    }
}

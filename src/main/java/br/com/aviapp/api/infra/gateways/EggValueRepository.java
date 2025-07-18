package br.com.aviapp.api.infra.gateways;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.application.gateways.IEggValue;
import br.com.aviapp.api.infra.mappers.EggValueMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEggValueEntity;
import br.com.aviapp.api.infra.mysql.repository.EggValueRepositoryJPA;

@Repository
public class EggValueRepository implements IEggValue {

    private final EggValueRepositoryJPA repositoryJPA;
    private final EggValueMapperEntity mapperEntity;

    public EggValueRepository(EggValueRepositoryJPA repositoryJPA, EggValueMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
    }

    @Override
    public EggValueDTO createEggValue(EggValueDTO eggValueDTO) {
        MySqlEggValueEntity entity = mapperEntity.toEntity(eggValueDTO);
        MySqlEggValueEntity savedEntity = repositoryJPA.save(entity);
        return mapperEntity.toDTO(savedEntity);
    }

    @Override
    public List<EggValueDTO> listAllEggValues() {
        return mapperEntity.toDTOList(repositoryJPA.findAll());
    }

    @Override
    public void deleteEggValue(Long eggValueId) {
        repositoryJPA.deleteById(eggValueId);
    }

    @Override
    public EggValueDTO getLastInsertedEggValueByBatchId(Long batchId) {
        return  mapperEntity.toDTO(repositoryJPA.findLastInsertedEggValue(batchId).orElseThrow());
    }

}

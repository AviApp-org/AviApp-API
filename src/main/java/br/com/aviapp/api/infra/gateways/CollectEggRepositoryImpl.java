package br.com.aviapp.api.infra.gateways;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.infra.mappers.CollectEggMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectEggDataRepositoryJPA;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CollectEggRepositoryImpl implements CollectEggRepository{

    private final CollectEggMapperEntity collectEggMapper;
    private final CollectEggDataRepositoryJPA repositoryJPA;

    @Override
    public CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO) {
        MySqlCollectEggDataEntity entity = collectEggMapper.toEntity(collectEggDataDTO);
        MySqlCollectEggDataEntity savedEntity = repositoryJPA.save(entity);
        return collectEggMapper.toDTO(savedEntity);
    }
    
}

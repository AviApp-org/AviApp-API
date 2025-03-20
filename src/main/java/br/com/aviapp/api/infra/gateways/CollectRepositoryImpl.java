package br.com.aviapp.api.infra.gateways;


import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.infra.mappers.CollectMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectRepositoryJPA;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CollectRepositoryImpl implements CollectRepository{
    
    private final CollectRepositoryJPA collectRepositoryJPA;
    private final CollectMapperEntity collectMapper;

    @Override
    public CollectDTO createCollect(CollectDTO collectDTO) {
        MySqlCollectEntity collectEntity = collectMapper.toEntity(collectDTO);
        MySqlCollectEntity savedCollectEntity = collectRepositoryJPA.save(collectEntity);
        return collectMapper.toDTO(savedCollectEntity);
    }

    @Override
    public void deleteCollect(Long collectId) {
        collectRepositoryJPA.deleteById(collectId);
    }

    
}

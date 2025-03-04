package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.infra.mappers.CollectMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CollectRepositoryImpl implements CollectRepository{
    
    private final CollectRepositoryJPA collectRepositoryJPA;
    private final CollectMapperEntity collectMapper;
    private final EntityLookupRepository entityLookupRepository;

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

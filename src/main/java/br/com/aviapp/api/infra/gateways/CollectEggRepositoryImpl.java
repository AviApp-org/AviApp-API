package br.com.aviapp.api.infra.gateways;


import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.infra.mappers.CollectEggMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectEggDataRepositoryJPA;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CollectEggRepositoryImpl implements CollectEggRepository {

    private final CollectEggMapperEntity collectEggMapper;
    private final CollectEggDataRepositoryJPA repositoryJPA;


    @Override
    public CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO) {
        MySqlCollectEggDataEntity entity = collectEggMapper.toEntity(collectEggDataDTO);
        MySqlCollectEggDataEntity savedEntity = repositoryJPA.save(entity);
        return collectEggMapper.toDTO(savedEntity);
    }

    @Override
    public List<CollectEggDataDTO> listEggCollectByEmployee(Long employeeId) {
        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findEggCollectsByEmployeeId(employeeId);
        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectEggDataDTO> listEggCollectByAviary(Long aviaryId) {
        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findEggCollectsByAviaryId(aviaryId);
        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectEggDataDTO> getAllEggCollects() {
        return repositoryJPA.findAll().stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }


}

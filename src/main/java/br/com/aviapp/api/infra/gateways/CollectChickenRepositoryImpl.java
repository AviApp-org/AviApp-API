package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.infra.mappers.CollectChickenMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectChickenDataRepositoryJPA;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CollectChickenRepositoryImpl implements CollectChickenRepository {

    private final CollectChickenMapperEntity collectChickenMapper;
    private final CollectChickenDataRepositoryJPA repositoryJPA;
    private final EntityLookupRepository entityLookupRepository;

    @Override
    public CollectChickenDTO createCollectChickenData(CollectChickenDTO collectChickenDataDTO) {
        MySqlCollectChickenDataEntity entity = collectChickenMapper.toEntity(collectChickenDataDTO);
        MySqlCollectChickenDataEntity savedEntity = repositoryJPA.save(entity);
        return collectChickenMapper.toDTO(savedEntity);
    }

    @Override
    public List<CollectChickenDTO> listCollectChickenData() {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findAll();
        return collectChickenMapper.toDTOList(entities);
    }

    @Override
    public List<CollectChickenDTO> listChickenCollectByEmployee(Long employeeId) {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findChickenCollectsByEmployeeId(employeeId);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectChickenDTO> listChickenCollectByAviary(Long aviaryId) {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findChickenCollectsByAviaryId(aviaryId);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }


}

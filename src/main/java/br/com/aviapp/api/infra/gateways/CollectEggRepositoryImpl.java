package br.com.aviapp.api.infra.gateways;


import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.infra.mappers.EggDetailsMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;
import br.com.aviapp.api.infra.mysql.repository.EggDetailRepositoryJPA;
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
    private final EggDetailRepositoryJPA eggDetailRepositoryJPA;
    private final EggDetailsMapperEntity eggDetailMapper;

    @Override
    public CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO) {
        MySqlCollectEggDataEntity entity = collectEggMapper.toEntity(collectEggDataDTO);
        MySqlCollectEggDataEntity savedEntity = repositoryJPA.save(entity);

        List<EggDetailDTO> eggDetails = collectEggDataDTO.eggDetail();

        List<MySqlEggDetailEntity> eggDetailEntity = eggDetailMapper.toEntityList(eggDetails);

        for (MySqlEggDetailEntity eggDetail : eggDetailEntity) {
            eggDetail.setEggCollection(savedEntity);
            eggDetailRepositoryJPA.save(eggDetail);
        }

        return collectEggMapper.toDTO(savedEntity);
    }

    @Override
    public List<CollectEggDataDTO> listEggCollectByEmployee(Long employeeId) {
//        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findEggCollectsByEmployeeId(employeeId);
//        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());\
        return null;
    }

    @Override
    public List<CollectEggDataDTO> listEggCollectByAviary(Long aviaryId) {
//        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findEggCollectsByAviaryId(aviaryId);
//        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<CollectEggDataDTO> getAllEggCollects() {
        return repositoryJPA.findAll().stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }


}

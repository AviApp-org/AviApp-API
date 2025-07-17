package br.com.aviapp.api.infra.gateways;


import br.com.aviapp.api.application.dto.EggDetailDTO;
import br.com.aviapp.api.infra.mappers.EggDetailsMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEggDetailEntity;
import br.com.aviapp.api.infra.mysql.repository.EggDetailRepositoryJPA;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.ICollectEgg;
import br.com.aviapp.api.infra.mappers.CollectEggMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEggDataEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectEggDataRepositoryJPA;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CollectEggRepository implements ICollectEgg {
    private final CollectEggMapperEntity collectEggMapper;
    private final CollectEggDataRepositoryJPA repositoryJPA;
    private final EggDetailRepositoryJPA eggDetailRepositoryJPA;
    private final EggDetailsMapperEntity eggDetailMapper;

    @Override
    public CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO) {
        MySqlCollectEggDataEntity entity = collectEggMapper.toEntity(collectEggDataDTO);
        MySqlCollectEggDataEntity savedEntity = repositoryJPA.save(entity);

        List<EggDetailDTO> eggDetails = collectEggDataDTO.eggDetails();

        List<MySqlEggDetailEntity> eggDetailEntity = eggDetailMapper.toEntityList(eggDetails);

        for (MySqlEggDetailEntity eggDetail : eggDetailEntity) {
            eggDetail.setEggCollection(savedEntity);
            eggDetailRepositoryJPA.save(eggDetail);
        }

        return collectEggMapper.toDTO(savedEntity);
    }

    @Override
    public List<CollectEggDataDTO> listEggCollectByAviary(Long aviaryId) {
        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findByAviary(aviaryId);
        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectEggDataDTO> getAllEggCollects() {
        return repositoryJPA.findAll().stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectEggDataDTO> getEggCollectByDate(LocalDate date) {
        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findByCollectionDateIgnoringTime(date);
        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectEggDataDTO> getEggCollectByAviaryAndDate(Long aviaryId, LocalDate date) {
        List<MySqlCollectEggDataEntity> entities = repositoryJPA.findByAviaryAndCollectionDateIgnoringTime(aviaryId, date);
        return entities.stream().map(collectEggMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCollectEggData(Long id) {
        repositoryJPA.deleteById(id);
    }

    @Override
    public Optional<CollectEggDataDTO> getCollectEggDataById(Long id) {
        return repositoryJPA.findById(id).map(collectEggMapper::toDTO);
    }


}

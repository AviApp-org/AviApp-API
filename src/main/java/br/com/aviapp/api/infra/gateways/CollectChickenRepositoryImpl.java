package br.com.aviapp.api.infra.gateways;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CollectChickenDTO> listChickenCollectByAviary(Long aviaryId) {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findByAviary(aviaryId);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectChickenDTO> getChickenCollectByDate(LocalDateTime date) {
       List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findByCollectionDateIgnoringTime(date);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }


}

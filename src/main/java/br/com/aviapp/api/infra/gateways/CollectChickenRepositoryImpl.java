package br.com.aviapp.api.infra.gateways;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.repository.AviaryRepositoryJPA;
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

    private final EntityLookupRepository entityLookupRepository;
    private final CollectChickenMapperEntity collectChickenMapper;
    private final CollectChickenDataRepositoryJPA repositoryJPA;
    private final AviaryRepositoryJPA aviaryRepositoryJPA;

    @Override
    public CollectChickenDTO createCollectChickenData(CollectChickenDTO collectChickenDataDTO) {
        MySqlCollectChickenDataEntity entity = collectChickenMapper.toEntity(collectChickenDataDTO);

        Optional<MySqlAviaryEntity> aviaryOptional = entityLookupRepository.findAviaryById(collectChickenDataDTO.aviaryId());

        if (aviaryOptional.isPresent()) {

            MySqlAviaryEntity aviary = aviaryOptional.get();

            Integer currentRoosters = aviary.getCurrentAmountOfRoosters();
            Integer currentChickens = aviary.getCurrentAmountOfChickens();

            System.out.println("Current Roosters: " + currentRoosters);
            System.out.println("Current Chickens: " + currentChickens);

            currentRoosters -= collectChickenDataDTO.deadRoosters();
            currentChickens -= collectChickenDataDTO.deadChickens();

            currentRoosters = Math.max(0, currentRoosters);
            currentChickens = Math.max(0, currentChickens);

            aviary.setCurrentAmountOfRoosters(currentRoosters);
            aviary.setCurrentAmountOfChickens(currentChickens);

            aviaryRepositoryJPA.save(aviary);
        }

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

    @Override
    public List<CollectChickenDTO> getChickenCollectByAviaryAndDate(Long aviaryId, LocalDateTime date) {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findByAviaryAndCollectionDateIgnoringTime(aviaryId, date);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }


}

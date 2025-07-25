package br.com.aviapp.api.infra.gateways;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.repository.AviaryRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.infra.mappers.CollectChickenMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectChickenDataEntity;
import br.com.aviapp.api.infra.mysql.repository.CollectChickenDataRepositoryJPA;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CollectChickenRepository implements ICollectChicken {

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

            if (aviary.getCurrentAmountOfRoosters() < collectChickenDataDTO.deadRoosters()) {
                throw new IllegalArgumentException("The number of dead roosters exceeds the current amount in the aviary.");
            }

            if (aviary.getCurrentAmountOfChickens() < collectChickenDataDTO.deadChickens()) {
                throw new IllegalArgumentException("The number of dead chickens exceeds the current amount in the aviary.");
            }

            int currentRoosters = aviary.getCurrentAmountOfRoosters();
            int currentChickens = aviary.getCurrentAmountOfChickens();

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
    public List<CollectChickenDTO> getChickenCollectByDate(LocalDate date) {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findByCollectionDateIgnoringTime(date);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CollectChickenDTO> getChickenCollectByAviaryAndDate(Long aviaryId, LocalDate date) {
        List<MySqlCollectChickenDataEntity> entities = repositoryJPA.findByAviaryAndCollectionDateIgnoringTime(aviaryId, date);
        return entities.stream().map(collectChickenMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCollectChickenData(Long id) {
        repositoryJPA.deleteById(id);
    }

    @Override
    public Optional<CollectChickenDTO> getCollectChickenDataById(Long id) {
        return repositoryJPA.findById(id).map(collectChickenMapper::toDTO);
    }


}

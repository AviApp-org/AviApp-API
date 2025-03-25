package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.application.gateways.ChickenRepository;
import br.com.aviapp.api.infra.mappers.ChickenMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlChickenEntity;
import br.com.aviapp.api.infra.mysql.repository.ChickenRepositoryJPA;

@Repository
public class ChickenRepositoryImpl implements ChickenRepository {

    private final ChickenRepositoryJPA repositoryJPA;
    private final ChickenMapperEntity mapperEntity;

    public ChickenRepositoryImpl(ChickenRepositoryJPA repositoryJPA, ChickenMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
    }

    @Override
    public ChickenDTO createChicken(ChickenDTO chickenDTO) {
        MySqlChickenEntity entity = mapperEntity.toEntity(chickenDTO);
        MySqlChickenEntity savedEntity = repositoryJPA.save(entity);
        return mapperEntity.toDTO(savedEntity);
    }

    @Override
    public List<ChickenDTO> listAllChicken() {
        return mapperEntity.toDTOList(repositoryJPA.findAll());
    }

    @Override
    public Optional<ChickenDTO> updateChicken(ChickenDTO chickenDTO, Long id) {
        return repositoryJPA.findById(id)
                .map(existingChicken -> {
                    if (chickenDTO.currentRoosters() != null) {
                        existingChicken.setCurrentRoosters(chickenDTO.currentRoosters());
                    }
                    if (chickenDTO.currentChickens() != null) {
                        existingChicken.setCurrentChickens(chickenDTO.currentChickens());
                    }


                    MySqlChickenEntity savedEntity = repositoryJPA.save(existingChicken);
                    return mapperEntity.toDTO(savedEntity);
                });
    }
}

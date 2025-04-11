package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.gateways.AviaryRepository;
import br.com.aviapp.api.infra.mappers.AviaryMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlBatchEntity;
import br.com.aviapp.api.infra.mysql.repository.AviaryRepositoryJPA;

@Repository
public class AviaryRepositoryImpl implements AviaryRepository {

    private final AviaryRepositoryJPA aviaryRepositoryJPA;
    private final AviaryMapperEntity aviaryMapper;
    private final EntityLookupRepositoryImpl entityLookupRepository;

    public AviaryRepositoryImpl(AviaryRepositoryJPA aviaryRepositoryJPA, AviaryMapperEntity aviaryMapper,
            EntityLookupRepositoryImpl entityLookupRepository) {
        this.aviaryRepositoryJPA = aviaryRepositoryJPA;
        this.aviaryMapper = aviaryMapper;
        this.entityLookupRepository = entityLookupRepository;
    }

    @Override
    public AviaryDTO createAviary(AviaryDTO aviaryDTO) {
        MySqlAviaryEntity aviaryEntity = aviaryMapper.toEntity(aviaryDTO);
        MySqlAviaryEntity savedAviaryEntity = aviaryRepositoryJPA.save(aviaryEntity);
        return aviaryMapper.toDTO(savedAviaryEntity);
    }

    @Override
    public Optional<List<AviaryDTO>> listAllAviarysByBatchId(Long batchId) {
        Optional<MySqlBatchEntity> batchEntity = entityLookupRepository.findBatchById(batchId);
        if (batchEntity.isEmpty()) {
            return Optional.empty();
        }
        List<MySqlAviaryEntity> entities = aviaryRepositoryJPA.findByBatchId(batchEntity.get());
        List<AviaryDTO> aviaryDTOs = entities.stream()
                .map(aviaryMapper::toDTO)
                .toList();
        return Optional.of(aviaryDTOs);
    }


    @Override
    public Optional<AviaryDTO> findAviaryById(Long aviaryId) {
        return aviaryRepositoryJPA.findById(aviaryId)
                .map(aviaryMapper::toDTO);
    }

    @Override
    public List<AviaryDTO> listAllAviarys() {
        return aviaryMapper.toDTOList(aviaryRepositoryJPA.findAll());
    }

    @Override
    public Optional<AviaryDTO> updateAviary(Long aviaryId, AviaryDTO aviaryDTO) {
        return aviaryRepositoryJPA.findById(aviaryId)
                .map(existingAviary -> {
                    MySqlAviaryEntity aviaryToUpdate = aviaryMapper.toEntity(aviaryDTO);
                    aviaryToUpdate.setId(aviaryId);
                    MySqlAviaryEntity updatedAviary = aviaryRepositoryJPA.save(aviaryToUpdate);
                    return aviaryMapper.toDTO(updatedAviary);
                });
    }

    @Override
    public void deleteAviary(Long aviaryId) {
        aviaryRepositoryJPA.deleteById(aviaryId);
    }
}

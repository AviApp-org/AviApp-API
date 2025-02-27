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
    public List<CollectDTO> listCollectByEmployee(Long employeeId) {
        Optional<MySqlEmployeeEntity> employeeEntity = entityLookupRepository.findEmployeeById(employeeId);
        if (employeeEntity.isEmpty()) {
            return List.of(); // Retorna lista vazia se o funcionário não existir
        }
        List<MySqlCollectEntity> collects = collectRepositoryJPA.findByEmployee(employeeEntity.get());
        return collects.stream()
                .map(collectMapper::toDTO)
                .toList();
    }

    @Override
    public List<CollectDTO> listCollectByAviary(Long aviaryId) {
        Optional<MySqlAviaryEntity> aviaryEntity = entityLookupRepository.findAviaryById(aviaryId);
        if (aviaryEntity.isEmpty()) {
            return List.of(); // Retorna lista vazia se o aviário não existir
        }
        List<MySqlCollectEntity> collects = collectRepositoryJPA.findByAviary(aviaryEntity.get());
        return collects.stream()
                .map(collectMapper::toDTO)
                .toList();
    }

    @Override
    public List<CollectDTO> getAllCollects() {
        List<MySqlCollectEntity> collects = collectRepositoryJPA.findAll();
        return collectMapper.toDTOList(collects);
    }

    @Override
    public void deleteCollect(Long collectId) {
        collectRepositoryJPA.deleteById(collectId);
    }

    
}

package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlCollectEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class CollectMapperEntity {
    private final EntityLookupRepository lookupRepository;
    
    public CollectMapperEntity(EntityLookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    public MySqlCollectEntity toEntity(CollectDTO dto) {
        MySqlAviaryEntity aviary = lookupRepository.findAviaryById(dto.aviaryId())
            .orElseThrow(() -> new EntityNotFoundException("Aviary not found"));

        MySqlEmployeeEntity employee = lookupRepository.findEmployeeById(dto.employeeId())
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        MySqlCollectEntity collect = new MySqlCollectEntity();
        collect.setId(dto.id());
        collect.setAviary(aviary);
        collect.setEmployee(employee);
        collect.setTimestamp(dto.timestamp());
        
        return collect;
    }

    public CollectDTO toDTO(MySqlCollectEntity entity) {
        return new CollectDTO(
            entity.getId(),
            entity.getAviary().getId(),
            entity.getEmployee().getId(),
            entity.getTimestamp()
        );
    }

    public List<CollectDTO> toDTOList(List<MySqlCollectEntity> collects) {
        return collects.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlCollectEntity> toEntityList(List<CollectDTO> collects) {
        return collects.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

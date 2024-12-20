package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.domain.enums.EnumEmployeeRole;
import br.com.aviapp.api.infra.mysql.enums.EmployeeRole;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class EmployeeMapperEntity {
    private final EntityLookupRepository lookupRepository;

    public EmployeeMapperEntity(EntityLookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    public EmployeeDTO toDTO(MySqlEmployeeEntity entity) {
        return new EmployeeDTO(
                entity.getId(),
                entity.getName(),
                EnumEmployeeRole.valueOf(entity.getRole().name()),
                entity.getCreatedAt(),
                entity.getFarmId().getId());
    }

    public MySqlEmployeeEntity toEntity(EmployeeDTO dto) {
        MySqlFarmEntity farm = lookupRepository.findFarmById(dto.farmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));

        MySqlEmployeeEntity employee = new MySqlEmployeeEntity();
        employee.setId(dto.id());
        employee.setName(dto.name());
        EmployeeRole.valueOf(dto.role().name());
        employee.setCreatedAt(dto.createdAt());
        employee.setFarmId(farm);

        return employee;
    }

    public List<EmployeeDTO> toDTOList(List<MySqlEmployeeEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlEmployeeEntity> toEntityList(List<EmployeeDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

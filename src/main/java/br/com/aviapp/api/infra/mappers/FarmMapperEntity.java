package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import jakarta.persistence.EntityNotFoundException;

public class FarmMapperEntity {
    private final EntityLookupRepository lookupRepository;

    public FarmMapperEntity(EntityLookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    public MySqlFarmEntity toEntity(FarmDTO dto) {
        MySqlClientEntity client = lookupRepository.findClientById(dto.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        List<MySqlEmployeeEntity> employees = dto.employeesId().stream()
                .map(id -> lookupRepository.findEmployeeById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Employee not found")))
                .collect(Collectors.toList());

        MySqlFarmEntity farm = new MySqlFarmEntity();
        farm.setId(dto.id());
        farm.setName(dto.name());
        farm.setClientId(client);
        farm.setEmployeeId(employees);
        farm.setStreet(dto.street());
        farm.setNumber(dto.number());
        farm.setCep(dto.cep());
        farm.setNeighborhood(dto.neighborhood());
        farm.setCity(dto.city());
        farm.setState(dto.state());
        return farm;
    }

    public FarmDTO toDTO(MySqlFarmEntity entity) {
        return new FarmDTO(
                entity.getId(),
                entity.getName(),
                entity.getClientId().getId(),
                entity.getEmployeeId().stream()
                        .map(MySqlEmployeeEntity::getId)
                        .collect(Collectors.toList()),
                entity.getStreet(),
                entity.getNumber(),
                entity.getCep(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState()
        );
    }

    public List<FarmDTO> toDTOList(List<MySqlFarmEntity> farms) {
        return farms.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlFarmEntity> toEntityList(List<FarmDTO> farms) {
        return farms.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}


package br.com.aviapp.api.application.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.AddressBO;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.entities.EmployeeBO;
import br.com.aviapp.api.domain.entities.FarmBO;
import jakarta.persistence.EntityNotFoundException;

public class EmployeeMapperBO {

    private final LookUpRepository lookUpRepository;

    public EmployeeMapperBO(LookUpRepository lookUpRepository) {
        this.lookUpRepository = lookUpRepository;
    }

    public EmployeeBO toBO(EmployeeDTO dto) {
        if (dto.farmId() == null) {
            throw new IllegalArgumentException("Farm ID is required");
        }

        Optional<FarmDTO> farmDTO = lookUpRepository.findFarmDTOById(dto.farmId());
        if (farmDTO.isEmpty()) {
            throw new EntityNotFoundException("Farm not found with id: " + dto.farmId());
        }

        FarmDTO farm = farmDTO.get();
        return new EmployeeBO(
                dto.id(),
                dto.name(),
                dto.cpf(),
                dto.birthDate(),
                dto.phone(),
                dto.role(),
                dto.createdAt(), // Set current date here
                new FarmBO(
                        farm.id(),
                        farm.name(),
                        new ClientBO(), // Create empty objects instead of null
                        new AddressBO(),
                        new ArrayList<>()));
    }

    public EmployeeDTO toDTO(EmployeeBO bo) {
        return new EmployeeDTO(
                bo.getId(),
                bo.getName(),
                bo.getCpf(),
                bo.getBirthDate(),
                bo.getPhone(),
                bo.getRole(),
                bo.getCreatedAt(),
                bo.getFarm().getId());
    }

    public List<EmployeeBO> toBOList(List<EmployeeDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> toDTOList(List<EmployeeBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

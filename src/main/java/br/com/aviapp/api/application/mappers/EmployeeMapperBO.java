package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.EmployeeBO;
import br.com.aviapp.api.domain.entities.FarmBO;

public class EmployeeMapperBO {

    private final LookUpRepository lookUpRepository;    
    
    public EmployeeMapperBO(LookUpRepository lookUpRepository) {
        this.lookUpRepository = lookUpRepository;
    }

    public EmployeeBO toBO(EmployeeDTO dto) {
        Optional<FarmDTO> farm = lookUpRepository.findFarmDTOById(dto.farmId());
        return new EmployeeBO(
            dto.id(),
            dto.name(),
            dto.cpf(),
            dto.phone(),
            dto.role(),
            dto.createdAt(),
            new FarmBO(
                farm.get().id(),
                farm.get().name(),
                null,
                null,
                null
            )
        );
    }

    public EmployeeDTO toDTO(EmployeeBO bo) {
        return new EmployeeDTO(
            bo.getId(),
            bo.getName(),
            bo.getCpf(),
            bo.getPhone(),
            bo.getRole(),
            bo.getCreatedAt(),
            bo.getFarm().getId()
        );
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

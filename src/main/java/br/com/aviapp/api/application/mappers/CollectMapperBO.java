package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.CollectBO;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class CollectMapperBO {
    private final LookUpRepository lookupRepository;
    private final AviaryMapperBO aviaryMapper;
    private final EmployeeMapperBO employeeMapper;

    public CollectMapperBO(
        LookUpRepository lookupRepository,
        AviaryMapperBO aviaryMapper,
        EmployeeMapperBO employeeMapper
    ) {
        this.lookupRepository = lookupRepository;
        this.aviaryMapper = aviaryMapper;
        this.employeeMapper = employeeMapper;
    }

    public CollectBO toBO(CollectDTO dto) {
        var aviary = lookupRepository.findAviaryDTOById(dto.aviaryId())
            .orElseThrow(() -> new EntityNotFoundException("Aviary not found"));
        
        var employee = lookupRepository.findEmployeeDTOById(dto.employeeId())
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        return new CollectBO(
            dto.id(),
            aviaryMapper.toBO(aviary),
            employeeMapper.toBO(employee),
            dto.timestamp()
        );
    }

    public CollectDTO toDTO(CollectBO bo) {
        return new CollectDTO(
            bo.getId(),
            bo.getAviary().getId(),
            bo.getEmployee().getId(),
            bo.getTimestamp()
        );
    }

    public List<CollectBO> toBOList(List<CollectDTO> dtos) {
        return dtos.stream()
            .map(this::toBO)
            .collect(Collectors.toList());
    }

    public List<CollectDTO> toDTOList(List<CollectBO> bos) {
        return bos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}

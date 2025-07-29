package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.entities.EmployeeBO;
import br.com.aviapp.api.domain.entities.FarmBO;
import jakarta.persistence.EntityNotFoundException;

public class FarmMapperBO {
    private final ILookUp lookupRepository;
    private final ClientMapperBO clientMapper;
    private final EmployeeMapperBO employeeMapper;

    public FarmMapperBO(
        ILookUp lookupRepository,
        ClientMapperBO clientMapper,
        EmployeeMapperBO employeeMapper
    ) {
        this.lookupRepository = lookupRepository;
        this.clientMapper = clientMapper;
        this.employeeMapper = employeeMapper;
    }

    public FarmBO toBO(FarmDTO dto) {
        Optional<ClientDTO> client = lookupRepository.findClientDTOById(dto.clientId());
        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }
        ClientBO clientBO = clientMapper.toBO(client.get());


        List<EmployeeDTO> employees = dto.employeesId().stream()
                .map(id -> lookupRepository.findEmployeeDTOById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Employee not found")))
                .collect(Collectors.toList());
        List<EmployeeBO> employeeBOs = employeeMapper.toBOList(employees);

        return new FarmBO(
                dto.id(),
                dto.name(),
                clientBO,
                employeeBOs,
                dto.street(),
                dto.number(),
                dto.cep(),
                dto.neighborhood(),
                dto.city(),
                dto.state());
    }

    public FarmDTO toDTO(FarmBO bo) {
        return new FarmDTO(
                bo.getId(),
                bo.getName(),
                bo.getClient().getId(),
                bo.getEmployees().stream()
                        .map(EmployeeBO::getId)
                        .collect(Collectors.toList()),
                bo.getStreet(),
                bo.getNumber(),
                bo.getCep(),
                bo.getNeighborhood(),
                bo.getCity(),
                bo.getState());
    }
}

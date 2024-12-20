package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.domain.entities.AddressBO;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.domain.entities.EmployeeBO;
import br.com.aviapp.api.domain.entities.FarmBO;
import jakarta.persistence.EntityNotFoundException;

public class FarmMapperBO {
    private final LookUpRepository lookupRepository;
    private final ClientMapperBO clientMapper;
    private final AddressMapperBO addressMapper;
    private final EmployeeMapperBO employeeMapper;

    public FarmMapperBO(
        LookUpRepository lookupRepository,
        ClientMapperBO clientMapper,
        AddressMapperBO addressMapper,
        EmployeeMapperBO employeeMapper
    ) {
        this.lookupRepository = lookupRepository;
        this.clientMapper = clientMapper;
        this.addressMapper = addressMapper;
        this.employeeMapper = employeeMapper;
    }

    public FarmBO toBO(FarmDTO dto) {
        Optional<ClientDTO> client = lookupRepository.findClientDTOById(dto.clientId());
        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }
        ClientBO clientBO = clientMapper.toBO(client.get());

        Optional<AddressDTO> address = lookupRepository.findAddressDTOById(dto.addressId());
        if (address.isEmpty()) {
            throw new EntityNotFoundException("Address not found");
        }
        AddressBO addressBO = addressMapper.toBO(address.get());

        List<EmployeeDTO> employees = dto.employeesId().stream()
                .map(id -> lookupRepository.findEmployeeDTOById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Employee not found")))
                .collect(Collectors.toList());
        List<EmployeeBO> employeeBOs = employeeMapper.toBOList(employees);

        return new FarmBO(
                dto.id(),
                dto.name(),
                clientBO,
                addressBO,
                employeeBOs);
    }

    public FarmDTO toDTO(FarmBO bo) {
        return new FarmDTO(
                bo.getId(),
                bo.getName(),
                bo.getClient().getId(),
                bo.getAddress().getId(),
                bo.getEmployees().stream()
                        .map(EmployeeBO::getId)
                        .collect(Collectors.toList()));
    }
}

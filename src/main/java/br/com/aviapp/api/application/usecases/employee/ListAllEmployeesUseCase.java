package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.IEmployee;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllEmployeesUseCase {

    private final IEmployee repository;
    private final EmployeeMapperBO mapperBO;

    public ListAllEmployeesUseCase(IEmployee repository, EmployeeMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public List<EmployeeDTO> invoke() {
        return repository.listAllEmployees().stream()
                .map(mapperBO::toBO)
                .map(mapperBO::toDTO)
                .collect(Collectors.toList());
    }
}

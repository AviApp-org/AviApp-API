package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.domain.entities.EmployeeBO;

public class CreateEmployeeUseCase {

    private final EmployeeRepository repository;
    private final EmployeeMapperBO mapperBO;

    public CreateEmployeeUseCase(EmployeeRepository repository, EmployeeMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public EmployeeDTO invoke(EmployeeDTO employeeDTO) {
        // Validate and convert to business object
        EmployeeBO employeeBO = mapperBO.toBO(employeeDTO);
        // Convert back and create
        return repository.createEmployee(mapperBO.toDTO(employeeBO));
    }
    
}

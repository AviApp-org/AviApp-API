package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.domain.entities.EmployeeBO;

import java.util.Optional;

public class UpdateEmployeeUseCase {

    private final EmployeeRepository repository;
    private final EmployeeMapperBO mapperBO;

    public UpdateEmployeeUseCase(EmployeeRepository repository, EmployeeMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public Optional<EmployeeDTO> invoke(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeBO employeeBO = mapperBO.toBO(employeeDTO);

        EmployeeDTO validatedEmployee = mapperBO.toDTO(employeeBO);

        return repository.updateEmployee(employeeId,validatedEmployee);
    }
}

package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.IEmployee;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;

import java.util.Optional;

public class UpdateEmployeeUseCase {

    private final IEmployee repository;
    private final EmployeeMapperBO mapperBO;

    public UpdateEmployeeUseCase(IEmployee repository, EmployeeMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public Optional<EmployeeDTO> invoke(Long employeeId, EmployeeDTO employeeDTO) {
//        EmployeeBO employeeBO = mapperBO.toBO(employeeDTO);
//
//        EmployeeDTO validatedEmployee = mapperBO.toDTO(employeeBO);

        return repository.updateEmployee(employeeId,employeeDTO);
    }
}

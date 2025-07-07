package br.com.aviapp.api.application.usecases.employee;

import java.util.Optional;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.IEmployee;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;

public class FindEmployeeByIdUseCase {
    private final IEmployee employeeRepository;
    private final EmployeeMapperBO employeeMapper;

    public FindEmployeeByIdUseCase(IEmployee employeeRepository, EmployeeMapperBO employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Optional<EmployeeDTO> invoke(Long employeeId) {
        return employeeRepository.findEmployee(employeeId)
                .map(employeeMapper::toBO)
                .map(employeeMapper::toDTO);
    }
}

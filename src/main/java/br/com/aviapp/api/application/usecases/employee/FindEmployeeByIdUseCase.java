package br.com.aviapp.api.application.usecases.employee;

import java.util.Optional;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;

public class FindEmployeeByIdUseCase {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapperBO employeeMapper;

    public FindEmployeeByIdUseCase(EmployeeRepository employeeRepository, EmployeeMapperBO employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Optional<EmployeeDTO> invoke(Long employeeId) {
        return employeeRepository.findEmployee(employeeId)
                .map(employeeMapper::toBO)
                .map(employeeMapper::toDTO);
    }
}

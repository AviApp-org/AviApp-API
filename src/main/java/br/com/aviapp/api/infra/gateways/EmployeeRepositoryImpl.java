package br.com.aviapp.api.infra.gateways;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.infra.mappers.EmployeeMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EmployeeRepositoryJPA;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeRepositoryJPA employeeRepositoryJPA;
    private final EmployeeMapperEntity employeeMapper;

    public EmployeeRepositoryImpl(EmployeeRepositoryJPA employeeRepositoryJPA, EmployeeMapperEntity employeeMapper) {
        this.employeeRepositoryJPA = employeeRepositoryJPA;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Optional<EmployeeDTO> findEmployee(Long id) {
        return employeeRepositoryJPA.findById(id)
                .map(employeeMapper::toDTO);
    }

}

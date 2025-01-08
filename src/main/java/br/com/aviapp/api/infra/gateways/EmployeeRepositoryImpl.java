package br.com.aviapp.api.infra.gateways;

import java.util.List;
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
    public List<EmployeeDTO> listAllEmployees() {
        return employeeMapper.toDTOList(employeeRepositoryJPA.findAll());
    }

    @Override
    public Optional<EmployeeDTO> findEmployee(Long id) {
        return employeeRepositoryJPA.findById(id)
                .map(employeeMapper::toDTO);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) {

    }

    @Override
    public Optional<EmployeeDTO> updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        return Optional.empty();
    }

}

package br.com.aviapp.api.infra.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.infra.mysql.enums.EmployeeRole;
import br.com.aviapp.api.infra.mysql.models.MySqlEmployeeEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.IEmployee;
import br.com.aviapp.api.infra.mappers.EmployeeMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EmployeeRepositoryJPA;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;

@Repository
public class EmployeeRepository implements IEmployee {
    private final EmployeeRepositoryJPA employeeRepositoryJPA;
    private final EmployeeMapperEntity employeeMapper;
    private final FarmRepositoryJPA farmRepositoryJPA;

    public EmployeeRepository(EmployeeRepositoryJPA employeeRepositoryJPA, EmployeeMapperEntity employeeMapper, FarmRepositoryJPA farmRepositoryJPA) {
        this.employeeRepositoryJPA = employeeRepositoryJPA;
        this.employeeMapper = employeeMapper;
        this.farmRepositoryJPA = farmRepositoryJPA;
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
        MySqlEmployeeEntity entity = employeeMapper.toEntity(employeeDTO);

        // Save the employee
        MySqlEmployeeEntity savedEmployee = employeeRepositoryJPA.save(entity);

        // Update the farm's employee list
        MySqlFarmEntity farm = entity.getFarmId();
        if (farm.getEmployeeId() == null) {
            farm.setEmployeeId(new ArrayList<>());
        }
        farm.getEmployeeId().add(savedEmployee);
        farmRepositoryJPA.save(farm);

        return employeeMapper.toDTO(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepositoryJPA.deleteById(employeeId);
    }

    @Override
    public Optional<EmployeeDTO> updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        return employeeRepositoryJPA.findById(employeeId)
                .map(existingEmployee -> {
                    if (employeeDTO.cpf() != null) {
                        existingEmployee.setCpf(employeeDTO.cpf());
                    }
                    if (employeeDTO.name() != null) {
                        existingEmployee.setName(employeeDTO.name());
                    }
                    if (employeeDTO.phone() != null) {
                        existingEmployee.setPhone(employeeDTO.phone());
                    }
                    if (employeeDTO.role() != null) {
                        existingEmployee.setRole(EmployeeRole.valueOf(employeeDTO.role().name()));
                    }

                    MySqlEmployeeEntity savedEntity = employeeRepositoryJPA.save(existingEmployee);
                    return employeeMapper.toDTO(savedEntity);
                });
    }

}

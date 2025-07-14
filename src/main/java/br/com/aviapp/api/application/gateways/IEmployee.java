package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.EmployeeDTO;

public interface IEmployee {

    List<EmployeeDTO> listAllEmployees();

    Optional<EmployeeDTO> findEmployee(Long id);

    List<EmployeeDTO> getEmployeeByFarmId(Long farmId);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long employeeId);

    Optional<EmployeeDTO> updateEmployee(Long employeeId, EmployeeDTO employeeDTO);
}

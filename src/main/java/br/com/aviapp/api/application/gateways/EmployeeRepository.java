package br.com.aviapp.api.application.gateways;

import java.util.Optional;

import br.com.aviapp.api.application.dto.EmployeeDTO;

public interface EmployeeRepository {
    
    Optional<EmployeeDTO> findEmployee(Long id);
}

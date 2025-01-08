package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;

import java.util.List;

public class ListAllEmployeesUseCase {

    private final EmployeeRepository repository;

    public ListAllEmployeesUseCase(EmployeeRepository repository) {
        this.repository = repository;

    }

    public List<EmployeeDTO> invoke() {
        return repository.listAllEmployees();
    }
}

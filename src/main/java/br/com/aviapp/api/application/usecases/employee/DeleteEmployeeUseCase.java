package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;

public class DeleteEmployeeUseCase {

    private final EmployeeRepository repository;

    public DeleteEmployeeUseCase(EmployeeRepository repository) {
        this.repository = repository;

    }

    public void invoke(Long employeeId) {
        repository.deleteEmployee(employeeId);
    }
}

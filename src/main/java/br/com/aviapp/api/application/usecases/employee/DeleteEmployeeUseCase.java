package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.gateways.IEmployee;

public class DeleteEmployeeUseCase {

    private final IEmployee repository;

    public DeleteEmployeeUseCase(IEmployee repository) {
        this.repository = repository;

    }

    public void invoke(Long employeeId) {
        repository.deleteEmployee(employeeId);
    }
}

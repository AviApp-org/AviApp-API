package br.com.aviapp.api.application.usecases.employee;

import br.com.aviapp.api.application.dto.EmployeeDTO;
import br.com.aviapp.api.application.gateways.IEmployee;

import java.util.List;

public class GetEmployeesByFarmIdUseCase {
    private final IEmployee repository;


    public GetEmployeesByFarmIdUseCase(IEmployee repository) {
        this.repository = repository;
    }

    public List<EmployeeDTO> invoke (Long farmId) {
        return repository.getEmployeeByFarmId(farmId);
    }
}

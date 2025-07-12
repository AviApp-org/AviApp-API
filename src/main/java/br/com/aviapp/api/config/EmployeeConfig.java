package br.com.aviapp.api.config;

import br.com.aviapp.api.application.usecases.employee.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.IEmployee;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.infra.mappers.EmployeeMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class EmployeeConfig {

    @Bean
    public UpdateEmployeeUseCase updateEmployeeUseCase(IEmployee employeeRepository, EmployeeMapperBO employeeMapperBO) {
        return new UpdateEmployeeUseCase(employeeRepository, employeeMapperBO);
    }

    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(IEmployee employeeRepository, EmployeeMapperBO employeeMapperBO) {
        return new CreateEmployeeUseCase(employeeRepository, employeeMapperBO);
    }

    @Bean
    public DeleteEmployeeUseCase deleteEmployeeUseCase(IEmployee employeeRepository) {
        return new DeleteEmployeeUseCase(employeeRepository);
    }

    @Bean
    public ListAllEmployeesUseCase listAllEmployeesUseCase(IEmployee employeeRepository, EmployeeMapperBO employeeMapperBO) {
        return new ListAllEmployeesUseCase(employeeRepository, employeeMapperBO);
    }

    @Bean GetEmployeesByFarmIdUseCase getEmployeesByFarmIdUseCase (IEmployee repository) {
        return new GetEmployeesByFarmIdUseCase(repository);
    }

    @Bean
    public FindEmployeeByIdUseCase findEmployeeById(IEmployee employeeRepository, EmployeeMapperBO employeeMapperBO) {
        return new FindEmployeeByIdUseCase(employeeRepository, employeeMapperBO);
    }

    @Bean
    public EmployeeMapperEntity employeeMapper(EntityLookupRepository entityLookupRepository) {
        return new EmployeeMapperEntity(entityLookupRepository);
    }

    @Bean
    public EmployeeMapperBO employeeMapperBO(ILookUp lookUpRepository) {
        return new EmployeeMapperBO(lookUpRepository);
    }
}

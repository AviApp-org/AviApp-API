package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.EmployeeRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.application.usecases.employee.FindEmployeeByIdUseCase;
import br.com.aviapp.api.infra.mappers.EmployeeMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class EmployeeConfig {

    @Bean
    FindEmployeeByIdUseCase findEmployeeById( EmployeeRepository employeeRepository, EmployeeMapperBO employeeMapperBO) {
        return new FindEmployeeByIdUseCase(employeeRepository, employeeMapperBO);
    }

    @Bean
    EmployeeMapperEntity employeeMapper(EntityLookupRepository entityLookupRepository) {
        return new EmployeeMapperEntity(entityLookupRepository);
    }

    @Bean
    EmployeeMapperBO employeeMapperBO(LookUpRepository lookUpRepository) {
        return new EmployeeMapperBO(lookUpRepository);
    }
}

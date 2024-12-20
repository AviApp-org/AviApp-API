package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.FarmRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.AddressMapperBO;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.application.mappers.FarmMapperBO;

import br.com.aviapp.api.application.usecases.farm.CreateFarmUseCase;
import br.com.aviapp.api.application.usecases.farm.FindFarmByIdUseCase;
import br.com.aviapp.api.infra.mappers.FarmMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class FarmConfig {


    @Bean
    FarmMapperEntity farmMapperEntity(EntityLookupRepository lookupRepository) {
        return new FarmMapperEntity(lookupRepository);
    }

    @Bean
    FarmMapperBO farmMapperBO(
            LookUpRepository lookupRepository,
            ClientMapperBO clientMapper,
            AddressMapperBO addressMapper,
            EmployeeMapperBO employeeMapper) {
        return new FarmMapperBO(lookupRepository, clientMapper, addressMapper, employeeMapper);
    }

    @Bean
    CreateFarmUseCase createFarmUseCase(FarmRepository repository) {
        return new CreateFarmUseCase(repository);
    }

    @Bean
    FindFarmByIdUseCase findFarmByIdUseCase(FarmRepository repository, FarmMapperBO mapperBO) {
        return new FindFarmByIdUseCase(repository, mapperBO);
    }
}

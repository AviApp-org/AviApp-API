package br.com.aviapp.api.config;

import br.com.aviapp.api.application.usecases.farm.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.IFarm;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.ClientMapperBO;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.application.mappers.FarmMapperBO;

import br.com.aviapp.api.infra.mappers.FarmMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class FarmConfig {


    @Bean
    public FarmMapperEntity farmMapperEntity(EntityLookupRepository lookupRepository) {
        return new FarmMapperEntity(lookupRepository);
    }

    @Bean
    public FarmMapperBO farmMapperBO(
            ILookUp lookupRepository,
            ClientMapperBO clientMapper,
            EmployeeMapperBO employeeMapper) {
        return new FarmMapperBO(lookupRepository, clientMapper, employeeMapper);
    }

    @Bean
    public CreateFarmUseCase createFarmUseCase(IFarm repository, FarmMapperBO mapperBO, GetFarmByClientIdUseCase getFarmByClientIdUseCase) {
        return new CreateFarmUseCase(repository, getFarmByClientIdUseCase, mapperBO);
    }

    @Bean
    public FindFarmByIdUseCase findFarmByIdUseCase(IFarm repository, FarmMapperBO mapperBO) {
        return new FindFarmByIdUseCase(repository, mapperBO);
    }

    @Bean
    public ListAllFarmsUseCase listAllFarmsUseCase(IFarm repository, FarmMapperBO mapperBO) {
        return new ListAllFarmsUseCase(repository, mapperBO);
    }

    @Bean
    public GetFarmByClientIdUseCase getFarmByClientIdUseCase(IFarm repository) {
        return new GetFarmByClientIdUseCase(repository);
    }

    @Bean
    public DeleteFarmByIdUseCase deleteFarmByIdUseCase(IFarm repository, FarmMapperBO mapperBO) {
        return new DeleteFarmByIdUseCase(repository, mapperBO);
    }
}

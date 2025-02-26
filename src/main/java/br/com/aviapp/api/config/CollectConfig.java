package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.CollectMapperBO;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.application.usecases.collect.createCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.deleteCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.listAllCollectsUseCase;
import br.com.aviapp.api.application.usecases.collect.listCollectsByAviaryUseCase;
import br.com.aviapp.api.application.usecases.collect.listCollectsByEmployeeUseCase;
import br.com.aviapp.api.infra.mappers.CollectMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectConfig {

    @Bean
    public createCollectUseCase createCollectUseCase(CollectRepository repository, CollectMapperBO collectMapperBO) {
        return new createCollectUseCase(repository, collectMapperBO);
    }

    @Bean
    public listAllCollectsUseCase listAllCollectsUseCase(CollectRepository repository,
            CollectMapperBO collectMapperBO) {
        return new listAllCollectsUseCase(repository, collectMapperBO);
    }

    @Bean
    public listCollectsByAviaryUseCase listCollectsByAviaryUseCase(CollectRepository repository,
            CollectMapperBO collectMapperBO) {
        return new listCollectsByAviaryUseCase(repository, collectMapperBO);
    }

    @Bean
    public listCollectsByEmployeeUseCase listCollectsByEmployeeUseCase(CollectRepository repository,
            CollectMapperBO collectMapperBO) {
        return new listCollectsByEmployeeUseCase(repository, collectMapperBO);
    }

    @Bean
    public deleteCollectUseCase deleteCollectUseCase(CollectRepository repository) {
        return new deleteCollectUseCase(repository);
    }

    @Bean
    public CollectMapperBO collectMapperBO(LookUpRepository lookUpRepository, AviaryMapperBO aviaryMapperBO,
            EmployeeMapperBO employeeMapperBO) {
        return new CollectMapperBO(lookUpRepository, aviaryMapperBO, employeeMapperBO);
    }

    @Bean
    public CollectMapperEntity collectMapperEntity(EntityLookupRepository entityLookupRepository) {
        return new CollectMapperEntity(entityLookupRepository);
    }
}

package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.CollectMapperBO;
import br.com.aviapp.api.application.mappers.EmployeeMapperBO;
import br.com.aviapp.api.application.usecases.collect.CreateCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.DeleteCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.ListAllCollectsUseCase;
import br.com.aviapp.api.application.usecases.collect.ListCollectsByAviaryUseCase;
import br.com.aviapp.api.application.usecases.collect.ListCollectsByEmployeeUseCase;
import br.com.aviapp.api.infra.mappers.CollectMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectConfig {

    @Bean
    public CreateCollectUseCase createCollectUseCase(CollectRepository repository, CollectMapperBO collectMapperBO) {
        return new CreateCollectUseCase(repository, collectMapperBO);
    }

    @Bean
    public ListAllCollectsUseCase listAllCollectsUseCase(CollectRepository repository,
            CollectMapperBO collectMapperBO) {
        return new ListAllCollectsUseCase(repository, collectMapperBO);
    }

    @Bean
    public ListCollectsByAviaryUseCase listCollectsByAviaryUseCase(CollectRepository repository,
            CollectMapperBO collectMapperBO) {
        return new ListCollectsByAviaryUseCase(repository, collectMapperBO);
    }

    @Bean
    public ListCollectsByEmployeeUseCase listCollectsByEmployeeUseCase(CollectRepository repository,
            CollectMapperBO collectMapperBO) {
        return new ListCollectsByEmployeeUseCase(repository, collectMapperBO);
    }

    @Bean
    public DeleteCollectUseCase deleteCollectUseCase(CollectRepository repository) {
        return new DeleteCollectUseCase(repository);
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

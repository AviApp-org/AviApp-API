package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.AviaryRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.application.usecases.aviary.CreateAviaryUseCase;
import br.com.aviapp.api.application.usecases.aviary.DeleteAviaryUseCase;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.infra.gateways.EntityLookupRepositoryImpl;
import br.com.aviapp.api.infra.mappers.AviaryMapperEntity;

@Configuration
public class AviaryConfig {

    @Bean
    public CreateAviaryUseCase createAviaryUseCase(AviaryRepository aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        return new CreateAviaryUseCase(aviaryRepository, aviaryMapperBO);
    }

    @Bean
    public ListAviariesByBatchUseCase listAviariesByBatchUseCase(AviaryRepository aviaryRepository) {
        return new ListAviariesByBatchUseCase(aviaryRepository);
    }

    @Bean
    public DeleteAviaryUseCase deleteAviaryUseCase(AviaryRepository aviaryRepository) {
        return new DeleteAviaryUseCase(aviaryRepository);
    }

    @Bean
    public FindAviaryByIdUseCase findAviaryByIdUseCase(AviaryRepository aviaryRepository,
            AviaryMapperBO aviaryMapperBO) {
        return new FindAviaryByIdUseCase(aviaryRepository, aviaryMapperBO);
    }

    @Bean
    public AviaryMapperBO aviaryMapperBO(BatchMapperBO batchMapperBO, LookUpRepository repository) {
        return new AviaryMapperBO(batchMapperBO, repository);
    }

    @Bean
    public AviaryMapperEntity aviaryMapperEntity(EntityLookupRepositoryImpl entityLookupRepository) {
        return new AviaryMapperEntity(entityLookupRepository);
    }
}

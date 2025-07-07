package br.com.aviapp.api.config;

import br.com.aviapp.api.application.usecases.aviary.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.IAviary;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.infra.gateways.EntityLookupRepository;
import br.com.aviapp.api.infra.mappers.AviaryMapperEntity;

@Configuration
public class AviaryConfig {

    @Bean
    public ListAllAviariesUseCase listAllAviariesUseCase(IAviary aviaryRepository) {
        return new ListAllAviariesUseCase(aviaryRepository);
    }

    @Bean
    public CreateAviaryUseCase createAviaryUseCase(IAviary aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        return new CreateAviaryUseCase(aviaryRepository, aviaryMapperBO);
    }

    @Bean
    public ListAviariesByBatchUseCase listAviariesByBatchUseCase(IAviary aviaryRepository) {
        return new ListAviariesByBatchUseCase(aviaryRepository);
    }

    @Bean
    public DeleteAviaryUseCase deleteAviaryUseCase(IAviary aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        return new DeleteAviaryUseCase(aviaryRepository, aviaryMapperBO);
    }

    @Bean
    public FindAviaryByIdUseCase findAviaryByIdUseCase(IAviary aviaryRepository,
                                                       AviaryMapperBO aviaryMapperBO) {
        return new FindAviaryByIdUseCase(aviaryRepository, aviaryMapperBO);
    }

    @Bean
    public UpdateAviaryUseCase updateAviaryUseCase(IAviary aviaryRepository, AviaryMapperBO aviaryMapperBO) {
        return new UpdateAviaryUseCase(aviaryRepository, aviaryMapperBO);
    }

    @Bean
    public AviaryMapperBO aviaryMapperBO(BatchMapperBO batchMapperBO, ILookUp repository) {
        return new AviaryMapperBO(batchMapperBO, repository);
    }

    @Bean
    public AviaryMapperEntity aviaryMapperEntity(EntityLookupRepository entityLookupRepository) {
        return new AviaryMapperEntity(entityLookupRepository);
    }
}

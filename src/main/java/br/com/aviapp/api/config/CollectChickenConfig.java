package br.com.aviapp.api.config;

import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.usecases.collectChicken.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.infra.mappers.CollectChickenMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectChickenConfig {

    @Bean
    public CreateChickenCollectUseCase createChickenCollectUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new CreateChickenCollectUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public ListAllChickenCollectUseCase listAllChickenCollectUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new ListAllChickenCollectUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public GetChickenCollectByDateUseCase getChickenCollectByDateUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new GetChickenCollectByDateUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new ListChickenCollectsByAviaryUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public ListChickenCollectsByDateAndAviaryUseCase listChickenCollectsByDateAndAviaryUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new ListChickenCollectsByDateAndAviaryUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public DeleteChickenCollectUseCase deleteChickenCollectUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new DeleteChickenCollectUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public CollectChickenMapperBO collectChickenMapper(ILookUp lookUpRepository, AviaryMapperBO aviaryMapperBO) {
        return new CollectChickenMapperBO(lookUpRepository, aviaryMapperBO);
    }

    @Bean
    public CollectChickenMapperEntity collectChickenMapperEntity(EntityLookupRepository entityLookupRepository) {
        return new CollectChickenMapperEntity(entityLookupRepository);
    }
}

package br.com.aviapp.api.config;

import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.usecases.collectChicken.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.infra.mappers.CollectChickenMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectChickenConfig {

    @Bean
    public CreateChickenCollectUseCase createChickenCollectUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new CreateChickenCollectUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public ListAllChickenCollectUseCase listAllChickenCollectUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new ListAllChickenCollectUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public GetChickenCollectByDateUseCase getChickenCollectByDateUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new GetChickenCollectByDateUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new ListChickenCollectsByAviaryUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public ListChickenCollectsByDateAndAviaryUseCase listChickenCollectsByDateAndAviaryUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new ListChickenCollectsByDateAndAviaryUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public DeleteChickenCollectUseCase deleteChickenCollectUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        return new DeleteChickenCollectUseCase(collectChickenRepository, collectChickenMapper);
    }

    @Bean
    public CollectChickenMapperBO collectChickenMapper(LookUpRepository lookUpRepository, AviaryMapperBO aviaryMapperBO) {
        return new CollectChickenMapperBO(lookUpRepository, aviaryMapperBO);
    }

    @Bean
    public CollectChickenMapperEntity collectChickenMapperEntity(EntityLookupRepository entityLookupRepository) {
        return new CollectChickenMapperEntity(entityLookupRepository);
    }
}

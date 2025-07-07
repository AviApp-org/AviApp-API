package br.com.aviapp.api.config;


import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.application.usecases.collectEgg.*;
import br.com.aviapp.api.infra.mappers.EggDetailsMapperEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.ICollectEgg;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.infra.mappers.CollectEggMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectEggConfig {

    @Bean
    public CreateEggCollectUseCase createEggCollectUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new CreateEggCollectUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new ListEggCollectsByAviaryUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public ListAllEggCollectsUseCase listAllEggCollectsUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new ListAllEggCollectsUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public GetEggCollectByDateUseCase getEggCollectByDateUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new GetEggCollectByDateUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new ListEggCollectsByDateAndAviaryUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public DeleteEggCollectUseCase deleteEggCollectUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new DeleteEggCollectUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public CollectEggMapperBO collectEggMapperBO(ILookUp lookUpRepository, AviaryMapperBO aviaryMapperBO, EggDetailMapperBO eggDetailMapperBO) {
        return new CollectEggMapperBO(lookUpRepository, aviaryMapperBO, eggDetailMapperBO);
    }

    @Bean
    public CollectEggMapperEntity collectEggMapperEntity(EntityLookupRepository entityLookupRepository, EggDetailsMapperEntity eggDetailsMapperEntity) {
        return new CollectEggMapperEntity(entityLookupRepository, eggDetailsMapperEntity);
    }

    @Bean
    public EggDetailMapperBO eggDetailMapperBO() {
        return new EggDetailMapperBO();
    }

    @Bean
    public EggDetailsMapperEntity eggDetailsMapperEntity() {
        return new EggDetailsMapperEntity();
    }
}

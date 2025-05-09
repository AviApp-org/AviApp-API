package br.com.aviapp.api.config;

import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.EggDetailMapperBO;
import br.com.aviapp.api.application.usecases.collectEgg.GetEggCollectByDateUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListAllEggCollectsUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByAviaryUseCase;
import br.com.aviapp.api.infra.mappers.EggDetailsMapperEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.application.usecases.collectEgg.CreateEggCollectUseCase;
import br.com.aviapp.api.infra.mappers.CollectEggMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectEggConfig {
    @Bean
    public CreateEggCollectUseCase createEggCollectUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new CreateEggCollectUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new ListEggCollectsByAviaryUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public ListAllEggCollectsUseCase listAllEggCollectsUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new ListAllEggCollectsUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public GetEggCollectByDateUseCase getEggCollectByDateUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        return new GetEggCollectByDateUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public CollectEggMapperBO collectEggMapperBO(LookUpRepository lookUpRepository, AviaryMapperBO aviaryMapperBO, EggDetailMapperBO eggDetailMapperBO) {
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

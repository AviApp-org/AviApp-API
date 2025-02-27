package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.application.mappers.CollectMapperBO;
import br.com.aviapp.api.application.usecases.collectEgg.CreateEggCollectUseCase;
import br.com.aviapp.api.infra.mappers.CollectEggMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;

@Configuration
public class CollectEggConfig {
    @Bean
    public CreateEggCollectUseCase createEggCollectUseCase(
            CollectEggRepository collectEggRepository,
            CollectEggMapperBO collectEggMapperBO) {
        return new CreateEggCollectUseCase(collectEggRepository, collectEggMapperBO);
    }

    @Bean
    public CollectEggMapperBO collectEggMapperBO(LookUpRepository lookUpRepository, CollectMapperBO collectMapper) {
        return new CollectEggMapperBO(lookUpRepository, collectMapper);
    }

    @Bean
    public CollectEggMapperEntity collectEggMapperEntity(EntityLookupRepository entityLookupRepository) {
        return new CollectEggMapperEntity(entityLookupRepository);
    }
}

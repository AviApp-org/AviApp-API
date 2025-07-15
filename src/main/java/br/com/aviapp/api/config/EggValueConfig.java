package br.com.aviapp.api.config;

import br.com.aviapp.api.application.gateways.IEggValue;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.application.mappers.EggValueMapperBO;
import br.com.aviapp.api.application.usecases.eggValue.CreateEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.DeleteEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.GetLastInsertedEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.ListAllEggValuesUseCase;
import br.com.aviapp.api.infra.gateways.EntityLookupRepository;
import br.com.aviapp.api.infra.mappers.EggValueMapperEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EggValueConfig {

    @Bean
    public EggValueMapperBO eggValueMapperBO(ILookUp lookUp, BatchMapperBO batchMapperBO) {
        return new EggValueMapperBO(lookUp, batchMapperBO);
    }

    @Bean
    public EggValueMapperEntity eggValueMapperEntity(EntityLookupRepository lookupRepository) {
        return new EggValueMapperEntity(lookupRepository);
    }

    @Bean
    public CreateEggValueUseCase createEggValueUseCase(EggValueMapperBO mapperBO, IEggValue repository) {
        return new CreateEggValueUseCase(repository, mapperBO);
    }

    @Bean
    public GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase(IEggValue repository, EggValueMapperBO mapperBO) {
        return new GetLastInsertedEggValueUseCase(repository, mapperBO);
    }

    @Bean
    public DeleteEggValueUseCase deleteEggValueUseCase(IEggValue repository) {
        return new DeleteEggValueUseCase(repository);
    }

    @Bean
    public ListAllEggValuesUseCase listAllEggValuesUseCase(IEggValue repository, EggValueMapperBO mapperBO) {
        return new ListAllEggValuesUseCase(repository, mapperBO);
    }
}
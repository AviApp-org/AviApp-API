package br.com.aviapp.api.config;

import br.com.aviapp.api.application.gateways.EggValueRepository;
import br.com.aviapp.api.application.mappers.EggValueMapperBO;
import br.com.aviapp.api.application.usecases.eggValue.CreateEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.DeleteEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.GetLastInsertedEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.ListAllEggValuesUseCase;
import br.com.aviapp.api.infra.mappers.EggValueMapperEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EggValueConfig {

    @Bean
    public EggValueMapperBO eggValueMapperBO() {
        return new EggValueMapperBO();
    }

    @Bean
    public EggValueMapperEntity eggValueMapperEntity() {
        return new EggValueMapperEntity();
    }

    @Bean
    public CreateEggValueUseCase createEggValueUseCase(EggValueMapperBO mapperBO, EggValueRepository repository) {
        return new CreateEggValueUseCase(repository, mapperBO);
    }

    @Bean
    public GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase(EggValueRepository repository, EggValueMapperBO mapperBO) {
        return new GetLastInsertedEggValueUseCase(repository, mapperBO);
    }

    @Bean
    public DeleteEggValueUseCase deleteEggValueUseCase(EggValueRepository repository) {
        return new DeleteEggValueUseCase(repository);
    }

    @Bean
    public ListAllEggValuesUseCase listAllEggValuesUseCase(EggValueRepository repository, EggValueMapperBO mapperBO) {
        return new ListAllEggValuesUseCase(repository, mapperBO);
    }
}
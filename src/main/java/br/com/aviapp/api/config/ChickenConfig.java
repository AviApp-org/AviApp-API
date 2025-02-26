package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.ChickenRepository;
import br.com.aviapp.api.application.mappers.ChickenMapperBO;
import br.com.aviapp.api.application.usecases.chicken.createChickenUseCase;
import br.com.aviapp.api.application.usecases.chicken.listChickensUseCase;
import br.com.aviapp.api.application.usecases.chicken.updateChickenUseCase;
import br.com.aviapp.api.infra.mappers.ChickenMapperEntity;

@Configuration
public class ChickenConfig {
    @Bean
    public createChickenUseCase createChickenUseCase(ChickenRepository chickenRepository,
            ChickenMapperBO chickenMapperBO) {
        return new createChickenUseCase(chickenRepository, chickenMapperBO);
    }

    @Bean
    public updateChickenUseCase updateChickenUseCase(ChickenRepository chickenRepository,
            ChickenMapperBO chickenMapperBO) {
        return new updateChickenUseCase(chickenRepository, chickenMapperBO);
    }

    @Bean
    listChickensUseCase listChickensUseCase(ChickenRepository chickenRepository, ChickenMapperBO chickenMapperBO) {
        return new listChickensUseCase(chickenRepository, chickenMapperBO);
    }

    @Bean
    public ChickenMapperBO chickenMapperBO() {
        return new ChickenMapperBO();
    }

    @Bean
    public ChickenMapperEntity chickenMapperEntity() {
        return new ChickenMapperEntity();
    }
}

package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.AnomalyRepository;
import br.com.aviapp.api.application.mappers.AnomalyMapperBO;
import br.com.aviapp.api.application.usecases.anomaly.CreateAnomalyUseCase;
import br.com.aviapp.api.application.usecases.anomaly.DeleteAnomalyUseCase;
import br.com.aviapp.api.application.usecases.anomaly.ListAllAnomaliesUseCase;
import br.com.aviapp.api.infra.mappers.AnomalyMapperEntity;

@Configuration
public class AnomalyConfig {

    @Bean
    public CreateAnomalyUseCase createAnomalyUseCase(AnomalyRepository repository, AnomalyMapperBO mapperBO) {
        return new CreateAnomalyUseCase(repository, mapperBO);
    }

    @Bean
    public DeleteAnomalyUseCase deleteAnomalyUseCase(AnomalyRepository repository) {
        return new DeleteAnomalyUseCase(repository);
    }

    @Bean
    public ListAllAnomaliesUseCase listAllAnomaliesUseCase(AnomalyRepository repository, AnomalyMapperBO mapperBO) {
        return new ListAllAnomaliesUseCase(repository, mapperBO);
    }



    @Bean
    public AnomalyMapperBO anomalyMapperBO() {
        return new AnomalyMapperBO();
    }

    @Bean
    public AnomalyMapperEntity anomalyMapperEntity() {
        return new AnomalyMapperEntity();
    }
}

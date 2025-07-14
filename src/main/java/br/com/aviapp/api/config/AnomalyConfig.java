package br.com.aviapp.api.config;

import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.usecases.anomaly.GetAnomalyByAviaryUseCase;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.IAnomaly;
import br.com.aviapp.api.application.mappers.AnomalyMapperBO;
import br.com.aviapp.api.application.usecases.anomaly.CreateAnomalyUseCase;
import br.com.aviapp.api.application.usecases.anomaly.DeleteAnomalyUseCase;
import br.com.aviapp.api.application.usecases.anomaly.ListAllAnomaliesUseCase;
import br.com.aviapp.api.infra.mappers.AnomalyMapperEntity;

@Configuration
public class AnomalyConfig {

    @Bean
    public CreateAnomalyUseCase createAnomalyUseCase(IAnomaly repository, AnomalyMapperBO mapperBO) {
        return new CreateAnomalyUseCase(repository, mapperBO);
    }

    @Bean
    public DeleteAnomalyUseCase deleteAnomalyUseCase(IAnomaly repository) {
        return new DeleteAnomalyUseCase(repository);
    }

    @Bean
    public ListAllAnomaliesUseCase listAllAnomaliesUseCase(IAnomaly repository, AnomalyMapperBO mapperBO) {
        return new ListAllAnomaliesUseCase(repository, mapperBO);
    }

    @Bean
    public GetAnomalyByAviaryUseCase getAnomalyByAviaryUseCase(IAnomaly repository) {
        return new GetAnomalyByAviaryUseCase(repository);
    }

    @Bean
    public AnomalyMapperBO anomalyMapperBO(FindAviaryByIdUseCase findAviaryByIdUseCase, AviaryMapperBO aviaryMapperBO) {
        return new AnomalyMapperBO(findAviaryByIdUseCase, aviaryMapperBO);
    }

    @Bean
    public AnomalyMapperEntity anomalyMapperEntity() {
        return new AnomalyMapperEntity();
    }
}

package br.com.aviapp.api.config;


import br.com.aviapp.api.application.gateways.BatchRepository;
import br.com.aviapp.api.application.gateways.LookUpRepository;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.application.mappers.FarmMapperBO;
import br.com.aviapp.api.application.usecases.batch.CreateBatchUseCase;
import br.com.aviapp.api.application.usecases.batch.DeactivateBatchUseCase;
import br.com.aviapp.api.application.usecases.batch.FindBatchByIdUseCase;
import br.com.aviapp.api.infra.mappers.BatchMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    CreateBatchUseCase createBatchUseCase(BatchRepository batchRepository, BatchMapperBO batchMapperBO) {
        return new CreateBatchUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    DeactivateBatchUseCase deactivateBatchUseCase(BatchRepository batchRepository, BatchMapperBO batchMapperBO) {
        return new DeactivateBatchUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    FindBatchByIdUseCase findBatchByIdUseCase(BatchRepository batchRepository, BatchMapperBO batchMapperBO) {
        return new FindBatchByIdUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    BatchMapperEntity batchMapperEntity(EntityLookupRepository repository) {
        return new BatchMapperEntity(repository);
    }

    @Bean
    BatchMapperBO batchMapperBO(FarmMapperBO farmMapperBO, LookUpRepository repository) {
        return new BatchMapperBO(farmMapperBO, repository);
    }
}

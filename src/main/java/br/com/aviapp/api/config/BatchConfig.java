package br.com.aviapp.api.config;


import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.application.mappers.FarmMapperBO;
import br.com.aviapp.api.application.usecases.batch.*;
import br.com.aviapp.api.infra.mappers.BatchMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    CreateBatchUseCase createBatchUseCase(IBatch batchRepository, BatchMapperBO batchMapperBO) {
        return new CreateBatchUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    DeactivateBatchUseCase deactivateBatchUseCase(IBatch batchRepository, BatchMapperBO batchMapperBO) {
        return new DeactivateBatchUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    ActivateBatchUseCase activateBatchUseCase(IBatch batchRepository, BatchMapperBO batchMapperBO) {
        return new ActivateBatchUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    UpdateBatchUseCase updateBatchUseCase(IBatch batchRepository, BatchMapperBO batchMapperBO) {
        return new UpdateBatchUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    FindBatchByIdUseCase findBatchByIdUseCase(IBatch batchRepository, BatchMapperBO batchMapperBO) {
        return new FindBatchByIdUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    ListBatchesByFarmIdUseCase listBatchesByFarmIdUseCase(IBatch batchRepository, BatchMapperBO batchMapperBO) {
        return new ListBatchesByFarmIdUseCase(batchRepository, batchMapperBO);
    }

    @Bean
    ListActiveBatchesByFarmUseCase listActiveBatchesByFarmUseCase (IBatch batchRepository) {
        return new ListActiveBatchesByFarmUseCase(batchRepository);
    }

    @Bean
    DeleteBatchUseCase deleteBatchUseCase(IBatch batchRepository) {
        return new DeleteBatchUseCase(batchRepository);
    }

    @Bean
    BatchMapperEntity batchMapperEntity(EntityLookupRepository repository) {
        return new BatchMapperEntity(repository);
    }

    @Bean
    BatchMapperBO batchMapperBO(FarmMapperBO farmMapperBO, ILookUp repository) {
        return new BatchMapperBO(farmMapperBO, repository);
    }
}

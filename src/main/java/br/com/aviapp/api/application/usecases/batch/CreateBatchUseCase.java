package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.BatchRepository;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;

public class CreateBatchUseCase {

    private final BatchRepository repository;

    private final BatchMapperBO mapper;

    public CreateBatchUseCase(BatchRepository repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public BatchDTO invoke(BatchDTO batchDTO){
        BatchBO batchBO = mapper.toBO(batchDTO);

        BatchDTO validatedBatch = mapper.toDTO(batchBO);

        return repository.createBatch(validatedBatch);
    }
}

package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.application.mappers.BatchMapperBO;

import java.util.Optional;

public class UpdateBatchUseCase {

    private final IBatch repository;
    private final BatchMapperBO mapper;

    public UpdateBatchUseCase(IBatch repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<BatchDTO> invoke(Long batchId, BatchDTO batch){
     //   BatchBO batchBO = mapper.toBO(batch);

       // BatchDTO validatedBatch = mapper.toDTO(batchBO);

        return repository.updateBatch(batchId, batch);
    }
}

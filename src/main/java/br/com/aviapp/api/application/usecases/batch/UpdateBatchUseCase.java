package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.BatchRepository;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;

import java.util.Optional;

public class UpdateBatchUseCase {

    private final BatchRepository repository;
    private final BatchMapperBO mapper;

    public UpdateBatchUseCase(BatchRepository repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<BatchDTO> invoke(Long batchId, BatchDTO batch){
     //   BatchBO batchBO = mapper.toBO(batch);

       // BatchDTO validatedBatch = mapper.toDTO(batchBO);

        return repository.updateBatch(batchId, batch);
    }
}

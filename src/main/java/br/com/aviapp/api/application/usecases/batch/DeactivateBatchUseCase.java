package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.BatchRepository;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;

import java.util.Optional;

public class DeactivateBatchUseCase {
    private final BatchRepository repository;
    private final BatchMapperBO mapper;

    public DeactivateBatchUseCase(BatchRepository repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<BatchDTO> invoke(Long batchId, boolean active) {
        return repository.findBatch(batchId)
                .map(batch -> {
                    BatchBO batchBO = mapper.toBO(batch);
                    batchBO.setActive(active);
                    return repository.updateBatch(batchId, mapper.toDTO(batchBO))
                            .orElse(null);  // Handle the case where update returns empty
                });
    }


}

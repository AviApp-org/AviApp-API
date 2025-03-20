package br.com.aviapp.api.application.usecases.batch;

import java.util.Optional;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.BatchRepository;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;

public class ActivateBatchUseCase {
     private final BatchRepository repository;
    private final BatchMapperBO mapper;

    public ActivateBatchUseCase(BatchRepository repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void invoke(Long batchId) {
        Optional<BatchDTO> batchDTO = repository.findBatch(batchId);
        BatchBO batchBO = mapper.toBO(batchDTO.get());

        batchBO.ativar();
        repository.save(mapper.toDTO(batchBO));
    }
}

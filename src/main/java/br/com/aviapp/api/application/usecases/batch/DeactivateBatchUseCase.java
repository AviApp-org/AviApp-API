package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;

import java.util.Optional;

public class DeactivateBatchUseCase {
    private final IBatch repository;
    private final BatchMapperBO mapper;

    public DeactivateBatchUseCase(IBatch repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void invoke(Long batchId) {
        Optional<BatchDTO> batchDTO = repository.findBatch(batchId);
        BatchBO batchBO = mapper.toBO(batchDTO.get());

        batchBO.desativar();
        repository.save(mapper.toDTO(batchBO));
    }

}

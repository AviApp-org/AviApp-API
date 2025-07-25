package br.com.aviapp.api.application.usecases.batch;

import java.util.Optional;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.application.mappers.BatchMapperBO;

public class FindBatchByIdUseCase {
    private final IBatch batchRepository;
    private final BatchMapperBO batchMapper;

    public FindBatchByIdUseCase(IBatch batchRepository, BatchMapperBO batchMapper) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
    }

    public Optional<BatchDTO> invoke(Long batchId) {
        return batchRepository.findBatch(batchId)
                .map(batchMapper::toBO)
                .map(batchMapper::toDTO);
    }
}

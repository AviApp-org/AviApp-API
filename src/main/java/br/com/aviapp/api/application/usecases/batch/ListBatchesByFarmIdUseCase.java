package br.com.aviapp.api.application.usecases.batch;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.application.mappers.BatchMapperBO;

public class ListBatchesByFarmIdUseCase {
    private final IBatch batchRepository;
    private final BatchMapperBO batchMapper;

    public ListBatchesByFarmIdUseCase(IBatch batchRepository, BatchMapperBO batchMapper) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
    }

    public List<BatchDTO> invoke(Long farmId) {
        return batchRepository.findBatchesByFarmId(farmId).stream()
                .map(batchMapper::toBO)
                .map(batchMapper::toDTO)
                .collect(Collectors.toList());
    }
}

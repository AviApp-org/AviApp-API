package br.com.aviapp.api.application.usecases.batch;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.gateways.IBatch;
import br.com.aviapp.api.application.mappers.BatchMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;

public class CreateBatchUseCase {

    private final IBatch repository;

    private final BatchMapperBO mapper;

    public CreateBatchUseCase(IBatch repository, BatchMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public BatchDTO invoke(BatchDTO batchDTO) throws BusinessRuleException {
        BatchBO batchBO = mapper.toBO(batchDTO);

        batchBO.validateForCreation();

        return repository.createBatch(mapper.toDTO(batchBO));
    }
}

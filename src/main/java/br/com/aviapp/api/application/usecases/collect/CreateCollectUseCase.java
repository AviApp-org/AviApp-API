package br.com.aviapp.api.application.usecases.collect;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.application.mappers.CollectMapperBO;
import br.com.aviapp.api.domain.entities.CollectBO;

public class CreateCollectUseCase {
    private final CollectRepository collectRepository;
    private final CollectMapperBO collectMapper;

    public CreateCollectUseCase(CollectRepository collectRepository, CollectMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public CollectDTO invoke(CollectDTO collectDTO) {
        CollectBO collectBO = collectMapper.toBO(collectDTO);
        if (collectBO.getAviary().getBatchId().getStatus().equals("INACTIVE")) {
            throw new RuntimeException("Batch is inactive");
        }
        return collectRepository.createCollect(collectMapper.toDTO(collectBO));
    }
}

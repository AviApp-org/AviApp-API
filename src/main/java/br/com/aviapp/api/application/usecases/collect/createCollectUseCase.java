package br.com.aviapp.api.application.usecases.collect;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.gateways.CollectRepository;
import br.com.aviapp.api.application.mappers.CollectMapperBO;
import br.com.aviapp.api.domain.entities.CollectBO;

public class createCollectUseCase {
    private final CollectRepository collectRepository;
    private final CollectMapperBO collectMapper;
    
    public createCollectUseCase(CollectRepository collectRepository, CollectMapperBO collectMapper) {
        this.collectRepository = collectRepository;
        this.collectMapper = collectMapper;
    }

    public CollectDTO invoke(CollectDTO collectDTO) {
        CollectBO collectBO = collectMapper.toBO(collectDTO);
        return collectRepository.createCollect(collectMapper.toDTO(collectBO));
    }
}

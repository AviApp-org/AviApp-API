package br.com.aviapp.api.application.usecases.collectChicken;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

public class ListAllChickenCollectUseCase {
    
    private final ICollectChicken collectChickenRepository;
    private final CollectChickenMapperBO collectChickenMapper;

    public ListAllChickenCollectUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        this.collectChickenRepository = collectChickenRepository;
        this.collectChickenMapper = collectChickenMapper;
    }

    public List<CollectChickenDTO> invoke(){
        List<CollectChickenDTO> collectChickenDTOList = collectChickenRepository.listCollectChickenData();
        return collectChickenDTOList.stream()
        .map(collectChickenMapper::toBO)
        .map(collectChickenMapper::toDTO)
        .collect(Collectors.toList());
    }
}

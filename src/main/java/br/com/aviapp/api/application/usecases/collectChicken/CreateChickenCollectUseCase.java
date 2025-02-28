package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.domain.entities.CollectChickenBO;

public class CreateChickenCollectUseCase {
    
    private final CollectChickenRepository collectChickenRepository;
    private final CollectChickenMapperBO collectChickenMapper;

    public CreateChickenCollectUseCase(CollectChickenRepository collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        this.collectChickenRepository = collectChickenRepository;
        this.collectChickenMapper = collectChickenMapper;
    }

    public CollectChickenDTO invoken(CollectChickenDTO chickenDTO) {
        CollectChickenBO collectChickenBO = collectChickenMapper.toBO(chickenDTO);
        CollectChickenDTO validatedChickenDTO = collectChickenMapper.toDTO(collectChickenBO);
        return collectChickenRepository.createCollectChickenData(validatedChickenDTO);
    }
}

package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.domain.entities.CollectChickenBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;

public class CreateChickenCollectUseCase {
    
    private final ICollectChicken collectChickenRepository;
    private final CollectChickenMapperBO collectChickenMapper;

    public CreateChickenCollectUseCase(ICollectChicken collectChickenRepository, CollectChickenMapperBO collectChickenMapper) {
        this.collectChickenRepository = collectChickenRepository;
        this.collectChickenMapper = collectChickenMapper;
    }

    public CollectChickenDTO invoke(CollectChickenDTO chickenDTO) throws BusinessRuleException {
        CollectChickenBO collectChickenBO = collectChickenMapper.toBO(chickenDTO);

        collectChickenBO.validateForCreation();

        return collectChickenRepository.createCollectChickenData(collectChickenMapper.toDTO(collectChickenBO));
    }
}

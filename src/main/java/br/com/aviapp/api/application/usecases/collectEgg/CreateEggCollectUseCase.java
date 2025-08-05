package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.ICollectEgg;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.errors.BusinessRuleException;

public class CreateEggCollectUseCase {

    private final ICollectEgg collectEggRepository;
    private final CollectEggMapperBO collectEggMapperBO;

    public CreateEggCollectUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        this.collectEggRepository = collectEggRepository;
        this.collectEggMapperBO = collectEggMapperBO;
    }

    public CollectEggDataDTO invoke(CollectEggDataDTO collectEggDataDTO) throws BusinessRuleException {
        CollectEggBO collectEggData = collectEggMapperBO.toBO(collectEggDataDTO);

        collectEggData.validateForCreation();

        CollectEggBO savedCollect = collectEggMapperBO.toBO(collectEggRepository.createCollectEgg(collectEggMapperBO.toDTO(collectEggData)));

        return collectEggMapperBO.toDTO(savedCollect);
    }
}

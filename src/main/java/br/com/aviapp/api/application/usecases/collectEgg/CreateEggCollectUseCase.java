package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.ICollectEgg;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;

public class CreateEggCollectUseCase {
    
    private final ICollectEgg collectEggRepository;
    private final CollectEggMapperBO collectEggMapperBO;

    public CreateEggCollectUseCase(ICollectEgg collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        this.collectEggRepository = collectEggRepository;
        this.collectEggMapperBO = collectEggMapperBO;
    }

    public CollectEggDataDTO invoke(CollectEggDataDTO collectEggDataDTO) {
        CollectEggBO collectEggData = collectEggMapperBO.toBO(collectEggDataDTO);

        return collectEggRepository.createCollectEgg(collectEggMapperBO.toDTO(collectEggData));
    }
}

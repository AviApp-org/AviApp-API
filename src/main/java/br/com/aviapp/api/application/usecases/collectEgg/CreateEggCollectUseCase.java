package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;

public class CreateEggCollectUseCase {
    
    private final CollectEggRepository collectEggRepository;
    private final CollectEggMapperBO collectEggMapperBO;

    public CreateEggCollectUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        this.collectEggRepository = collectEggRepository;
        this.collectEggMapperBO = collectEggMapperBO;
    }

    public CollectEggDataDTO invoke(CollectEggDataDTO collectEggDataDTO) {
        CollectEggBO collectEggData = collectEggMapperBO.toBO(collectEggDataDTO);

        return collectEggRepository.createCollectEgg(collectEggMapperBO.toDTO(collectEggData));
    }
}

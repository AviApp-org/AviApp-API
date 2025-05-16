package br.com.aviapp.api.application.usecases.collectEgg;

import br.com.aviapp.api.application.gateways.CollectEggRepository;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;

public class DeleteEggCollectUseCase {

    private final CollectEggRepository collectEggRepository;
    private final CollectEggMapperBO collectEggMapperBO;

    public DeleteEggCollectUseCase(CollectEggRepository collectEggRepository, CollectEggMapperBO collectEggMapperBO) {
        this.collectEggRepository = collectEggRepository;
        this.collectEggMapperBO = collectEggMapperBO;
    }

    public void invoke(Long id) {
        collectEggMapperBO.toBO(collectEggRepository.getCollectEggDataById(id).get());

        if (collectEggMapperBO != null) {
            collectEggRepository.deleteCollectEggData(id);
        }
    }
}

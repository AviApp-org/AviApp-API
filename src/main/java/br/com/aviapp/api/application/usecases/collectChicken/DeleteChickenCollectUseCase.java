package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.gateways.CollectChickenRepository;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.domain.entities.CollectChickenBO;

public class DeleteChickenCollectUseCase {

    private final CollectChickenRepository chickenRepository;
    private final CollectChickenMapperBO collectChickenMapperBO;

    public DeleteChickenCollectUseCase(CollectChickenRepository chickenRepository, CollectChickenMapperBO collectChickenMapperBO) {
        this.chickenRepository = chickenRepository;
        this.collectChickenMapperBO = collectChickenMapperBO;
    }

    public void invoke(Long id) {
        CollectChickenBO collectChickenBO = collectChickenMapperBO.toBO(chickenRepository.getCollectChickenDataById(id).get());

        if (collectChickenBO != null) {
            chickenRepository.deleteCollectChickenData(id);
        }
    }
}

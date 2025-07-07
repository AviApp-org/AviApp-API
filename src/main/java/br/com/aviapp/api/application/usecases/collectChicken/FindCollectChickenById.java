package br.com.aviapp.api.application.usecases.collectChicken;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.gateways.ICollectChicken;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;

import java.util.Optional;

public class FindCollectChickenById {

    private final ICollectChicken chickenRepository;
    private final CollectChickenMapperBO collectChickenMapperBO;

    public FindCollectChickenById(ICollectChicken chickenRepository, CollectChickenMapperBO collectChickenMapperBO) {
        this.chickenRepository = chickenRepository;
        this.collectChickenMapperBO = collectChickenMapperBO;
    }

    public Optional<CollectChickenDTO> invoke(Long id) {
        return chickenRepository.getCollectChickenDataById(id)
                .map(collectChickenMapperBO::toBO)
                .map(collectChickenMapperBO::toDTO);
    }

}

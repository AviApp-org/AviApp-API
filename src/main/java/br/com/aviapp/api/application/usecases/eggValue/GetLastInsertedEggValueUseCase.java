package br.com.aviapp.api.application.usecases.eggValue;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.application.gateways.IEggValue;
import br.com.aviapp.api.application.mappers.EggValueMapperBO;
import br.com.aviapp.api.domain.entities.EggValueBO;

public class GetLastInsertedEggValueUseCase {

    private final IEggValue repository;
    private final EggValueMapperBO mapperBO;

    public GetLastInsertedEggValueUseCase(IEggValue repository, EggValueMapperBO mapperBO) {
        this.repository = repository;
        this.mapperBO = mapperBO;
    }

    public EggValueDTO invoke() {

        EggValueBO eggValueBO = mapperBO.toBO(repository.getLastInsertedEggValue());

        return mapperBO.toDTO(eggValueBO);
    }
}

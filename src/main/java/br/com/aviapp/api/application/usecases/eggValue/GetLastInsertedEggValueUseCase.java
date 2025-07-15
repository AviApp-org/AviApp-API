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

    public EggValueDTO invoke(Long batchId) {

        EggValueBO eggValueBO = mapperBO.toBO(repository.getLastInsertedEggValueByBatchId(batchId));

        return mapperBO.toDTO(eggValueBO);
    }
}

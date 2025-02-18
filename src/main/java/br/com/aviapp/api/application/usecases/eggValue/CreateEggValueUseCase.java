package br.com.aviapp.api.application.usecases.eggValue;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.application.gateways.EggValueRepository;
import br.com.aviapp.api.application.mappers.EggValueMapperBO;
import br.com.aviapp.api.domain.entities.EggValueBO;

public class CreateEggValueUseCase {

    private final EggValueRepository repository;
    private final EggValueMapperBO mapper;

    public CreateEggValueUseCase(EggValueRepository repository, EggValueMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EggValueDTO invoke(EggValueDTO eggValueDTO) {
        EggValueBO eggValueBO = mapper.toBO(eggValueDTO);
        EggValueDTO validatedEggValue = mapper.toDTO(eggValueBO);
        return repository.createEggValue(validatedEggValue);
    }
}

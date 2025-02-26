package br.com.aviapp.api.application.usecases.chicken;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.application.gateways.ChickenRepository;
import br.com.aviapp.api.application.mappers.ChickenMapperBO;
import br.com.aviapp.api.domain.entities.ChickenBO;

public class createChickenUseCase {

    private final ChickenRepository repository;
    private final ChickenMapperBO mapper;

    public createChickenUseCase(ChickenRepository repository, ChickenMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ChickenDTO invoke(ChickenDTO chickenDTO) {

        ChickenBO chickenBO = mapper.toBO(chickenDTO);
        ChickenDTO validatedChicken = mapper.toDTO(chickenBO);
        return repository.createChicken(validatedChicken);
    }

}

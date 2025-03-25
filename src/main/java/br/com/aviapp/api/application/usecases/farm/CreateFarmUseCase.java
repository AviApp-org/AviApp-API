package br.com.aviapp.api.application.usecases.farm;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.gateways.FarmRepository;
import br.com.aviapp.api.application.mappers.FarmMapperBO;
import br.com.aviapp.api.domain.entities.BatchBO;
import br.com.aviapp.api.domain.entities.FarmBO;

import java.util.Optional;

public class CreateFarmUseCase {
    
    private final FarmRepository repository;
    private final FarmMapperBO mapper;

    public CreateFarmUseCase(FarmRepository repository, FarmMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FarmDTO invoke(FarmDTO farmDTO) {
        FarmBO farmBO = mapper.toBO(farmDTO);

        FarmDTO validatedFarm = mapper.toDTO(farmBO);

        return repository.createFarm(validatedFarm);
    }
    
}
